# Import necessary dependencies
import os
from pyspark.sql import SparkSession
from pyspark.ml import Pipeline
from pyspark.ml.regression import LinearRegression, GBTRegressor
from pyspark.ml.tuning import ParamGridBuilder, CrossValidator
from pyspark.ml.evaluation import RegressionEvaluator
from pyspark.ml.feature import StandardScaler
from pyspark.sql import functions as F
from pyspark.sql import Row
import numpy as np

# Helper function for running the commands
def run(command):
    return os.popen(command).read()


# Add here your team number teamx
team = 31

# location of your Hive database in HDFS
warehouse = "project/hive/warehouse"

spark = SparkSession.builder\
        .appName("{} - spark ML".format(team))\
        .master("yarn")\
        .config("hive.metastore.uris",
                "thrift://hadoop-02.uni.innopolis.ru:9883")\
        .config("spark.sql.warehouse.dir", warehouse)\
        .config("spark.sql.avro.compression.codec", "snappy")\
        .enableHiveSupport()\
        .getOrCreate()

# Ensure that we are connected by listing all the tables
spark.sql("USE team31_projectdb").show()
spark.sql("SHOW TABLES").show()
spark.sql("DROP TABLE IF EXISTS model1_predictions;")
spark.sql("DROP TABLE IF EXISTS model2_predictions;")
spark.sql("DROP TABLE IF EXISTS model1_feature_importance;")
spark.sql("DROP TABLE IF EXISTS model2_feature_importance;")


# Load files via HDFS
train_hdfs_path = "/user/team31/project/data/train"
test_hdfs_path = "/user/team31/project/data/test"

train_df = spark.read.parquet(train_hdfs_path)
test_df = spark.read.parquet(test_hdfs_path)

# Standard scaler for features
standard_scaler = StandardScaler(
    inputCol="features",
    outputCol="features_standardazied",
    withStd=True,
    withMean=True
).fit(train_df)

# Scale our datasets
train_df = standard_scaler.transform(train_df)
test_df = standard_scaler.transform(test_df)

print("Scaled dataframes.")

# Linear Regression
lr = LinearRegression(
    featuresCol="features_standardazied",
    labelCol="driver_pay",
    predictionCol="driver_pay_pred",
)

# GBT
gbt = GBTRegressor(
    featuresCol="features",
    labelCol="driver_pay",
    predictionCol="driver_pay_pred",
    seed=42,
)

# Evaluators (for the initial evaluation we will use RMSE, MAE, and R^2)
evaluators = {
    "rmse": RegressionEvaluator(
        metricName="rmse",
        labelCol="driver_pay",
        predictionCol="driver_pay_pred",
    ),
    "mae": RegressionEvaluator(
        metricName="mae",
        labelCol="driver_pay",
        predictionCol="driver_pay_pred",
    ),
    "r2": RegressionEvaluator(
        metricName="r2",
        labelCol="driver_pay",
        predictionCol="driver_pay_pred",
    )
}

# Fit both models
lr_fitted = lr.fit(train_df)
gbt_fitted = gbt.fit(train_df)

# Evaluate both models (not fine-tuned)
print("Linear Regression (not fine-tuned):")
test_df_pred = lr_fitted.transform(test_df)
for name, evaluator in evaluators.items():
    print(f"{name}: {evaluator.evaluate(test_df_pred):.6f}")

print("GBT (not fine-tuned):")
test_df_pred = gbt_fitted.transform(test_df)
for name, evaluator in evaluators.items():
    print(f"{name}: {evaluator.evaluate(test_df_pred):.6f}")

# Remove an unnecessary dataframe
del test_df_pred

# Set up Linear Regression hyperparameter space & CV pipeline
param_grid = (ParamGridBuilder()
              .addGrid(lr.regParam, [0.001, 0.01, 0.1, 1.0])
              .addGrid(lr.elasticNetParam, [0.0, 0.25, 0.5, 0.75, 1.0])
              .build())

evaluator = RegressionEvaluator(
    metricName="rmse",
    labelCol="driver_pay",
    predictionCol="driver_pay_pred",
)

lr_cross_validator = CrossValidator(
    estimator=lr,
    estimatorParamMaps=param_grid,
    evaluator=evaluator,
    numFolds=3
)

# Set up GBT hyperparameter space & CV pipeline
gbt_param_grid = (ParamGridBuilder()
                  .addGrid(gbt.maxDepth, [3, 4, 5])
                  .addGrid(gbt.stepSize, [0.05, 0.1])
                  .build())

gbt_cross_validator = CrossValidator(
    estimator=gbt,
    estimatorParamMaps=gbt_param_grid,
    evaluator=evaluator,
    numFolds=3,
    parallelism=4,
)

# Build the fine-tuning pipelines
lr_pipeline = Pipeline(
    stages=[
        lr_cross_validator,
    ]
)
lr_model = lr_pipeline.fit(train_df)
print("Linear Regression is successfully fine-tuned.")

gbt_pipeline = Pipeline(
    stages=[
        gbt_cross_validator,
    ]
)

gbt_model = gbt_pipeline.fit(train_df)
print("GBT is successfully fine-tuned.")

# Extract best models
best_lr_model = lr_model.stages[-1].bestModel
best_gbt_model = gbt_model.stages[-1].bestModel

# Save both models
best_lr_model.write().overwrite().save("/user/team31/project/models/model1")
best_gbt_model.write().overwrite().save("/user/team31/project/models/model2")

run("hdfs dfs -get /user/team31/project/models/model1 models/model1")
run("hdfs dfs -get /user/team31/project/models/model2 models/model2")

# Save models' predictions
predictions_lr = best_lr_model.transform(test_df)
predictions_lr.show()

predictions_lr.select("driver_pay", "driver_pay_pred")\
    .coalesce(1)\
    .write\
    .mode("overwrite")\
    .format("csv")\
    .option("sep", ",")\
    .option("header", "true")\
    .save("/user/team31/project/output/model1_predictions.csv")

# Run it from root directory of the repository
run("hdfs dfs -cat /user/team31/project/output/model1_predictions.csv/*.csv " \
    "> output/model1_predictions.csv")

# Do the same for GBT
predictions_gbt = best_gbt_model.transform(test_df)
predictions_gbt.show()

predictions_gbt.select("driver_pay", "driver_pay_pred")\
    .coalesce(1)\
    .write\
    .mode("overwrite")\
    .format("csv")\
    .option("sep", ",")\
    .option("header", "true")\
    .save("/user/team31/project/output/model2_predictions.csv")

# Run it from root directory of the repository
run("hdfs dfs -cat /user/team31/project/output/model2_predictions.csv/*.csv " \
    "> output/model2_predictions.csv")

print("Predictions are generated.")

# Register the transformed DataFrames
predictions_lr.createOrReplaceTempView("lr_predictions")
predictions_gbt.createOrReplaceTempView("gbt_predictions")

# Now execute the SQL query
spark.sql("""
    WITH metrics AS (
        SELECT
            'LinearRegression' as model,
            SQRT(AVG(POW(driver_pay - driver_pay_pred, 2))) as rmse,
            AVG(ABS(driver_pay - driver_pay_pred)) as mae,
            1 - SUM(POW(driver_pay - driver_pay_pred, 2))/NULLIF(SUM(POW(driver_pay - avg_pay, 2)), 0) as r2
        FROM (
            SELECT 
                driver_pay,
                driver_pay_pred,
                AVG(driver_pay) OVER () as avg_pay
            FROM lr_predictions
        )

        UNION ALL

        SELECT
            'GBTRegression' as model,
            SQRT(AVG(POW(driver_pay - driver_pay_pred, 2))) as rmse,
            AVG(ABS(driver_pay - driver_pay_pred)) as mae,
            1 - SUM(POW(driver_pay - driver_pay_pred, 2))/NULLIF(SUM(POW(driver_pay - avg_pay, 2)), 0) as r2
        FROM (
            SELECT
                driver_pay,
                driver_pay_pred,
                AVG(driver_pay) OVER () as avg_pay
            FROM gbt_predictions
        )
    )
    SELECT * FROM metrics
""").coalesce(1)\
    .write\
    .mode("overwrite")\
    .format("csv")\
    .option("sep", ",")\
    .option("header", "true")\
    .save("/user/team31/project/output/evaluation.csv")

# Run it from root directory of the repository
run("hdfs dfs -cat /user/team31/project/output/evaluation.csv/*.csv " \
    "> output/evaluation.csv")

# Now save results to Hive tables: predictions and feature importance
print("Saving to Hive")
lr_predictions = spark.read.csv(
    "/user/team31/project/output/model1_predictions.csv",
    header=True,
)
lr_predictions = lr_predictions.withColumn(
    "id",
    F.monotonically_increasing_id()
)

lr_predictions.write.mode("overwrite") \
    .saveAsTable("team31_projectdb.model1_predictions")

gbt_predictions = spark.read.csv(
    "/user/team31/project/output/model2_predictions.csv",
    header=True,
)
gbt_predictions = gbt_predictions.withColumn(
    "id",
    F.monotonically_increasing_id()
)

gbt_predictions.write.mode("overwrite") \
    .saveAsTable("team31_projectdb.model2_predictions")


# Define feature name
features = [
    'trip_miles',
    'trip_time',
    'base_passenger_fare',
    'tolls',
    'bcf',
    'sales_tax',
    'congestion_surcharge',
    'tips',
    'shared_request_flag',
    'shared_match_flag',
    'wav_request_flag',
    'wav_match_flag',
    'temperature',
    'feels_like',
    'dew_point',
    'humidity',
    'precipitation',
    'precipitation_prob',
    'snow',
    'snow_depth',
    'wind_speed',
    'wind_direction',
    'sea_level_pressure',
    'cloud_cover',
    'visibility',
    'uv_index',
    'borough_encoded_pu',
    'borough_encoded_pu_Manhattan',
    'borough_encoded_pu_Queens',
    'borough_encoded_pu_Brooklyn',
    'borough_encoded_pu_Bronx',
    'borough_encoded_pu_Staten Island',
    'borough_encoded_pu_Unknown',
    'borough_encoded_pu_EWR',
    'service_zone_encoded_pu',
    'service_zone_encoded_pu_Boro Zone',
    'service_zone_encoded_pu_Yellow Zone',
    'service_zone_encoded_pu_Airports',
    'service_zone_encoded_pu_N/A',
    'service_zone_encoded_pu_EWR',
    'borough_encoded_do',
    'borough_encoded_do_Manhattan',
    'borough_encoded_do_Queens',
    'borough_encoded_do_Brooklyn',
    'borough_encoded_do_Bronx',
    'borough_encoded_do_Staten Island',
    'borough_encoded_do_Unknown',
    'borough_encoded_do_EWR',
    'service_zone_encoded_do',
    'service_zone_encoded_do_Boro Zone',
    'service_zone_encoded_do_Yellow Zone',
    'service_zone_encoded_do_Airports',
    'service_zone_encoded_do_N/A',
    'service_zone_encoded_do_EWR',
    'day_sine',
    'day_cosine',
    'month_sine',
    'month_cosine',
    'hvfhs_license_num_encoded',
    'hvfhs_license_num_encoded_HV0003',
    'hvfhs_license_num_encoded_HV0005',
    'hvfhs_license_num_encoded_HV0004',
]
coefficients_lr = best_lr_model.coefficients.toArray()

coefficients_lr_rows = [
    Row(
        Feature=feature,
        Coefficient=float(coefficient)
    ) for feature, coefficient in zip(features, coefficients_lr)
]

lr_importance_df = spark.createDataFrame(coefficients_lr_rows)
lr_importance_df = lr_importance_df.withColumn(
    "Absolute_Coefficient",
    F.abs(lr_importance_df["Coefficient"])) \
                             .sort("Absolute_Coefficient", ascending=False)
lr_importance_df \
    .write.mode("overwrite") \
    .saveAsTable("team31_projectdb.model1_feature_importance")

print("Feature importance of the first model was saved.")

gbt_importance = best_gbt_model.featureImportances
dense_importance = np.zeros(gbt_importance.size)

for i in range(len(gbt_importance.indices)):
    idx = gbt_importance.indices[i]
    val = gbt_importance.values[i]
    dense_importance[idx] = val
dense_importance = gbt_importance.toArray()

coefficients_gbt_rows = [
    Row(
        Feature=feature,
        Importance=float(importance)
    ) for feature, importance in zip(features, dense_importance)
]

gbt_importance_df = spark.createDataFrame(coefficients_gbt_rows)
gbt_importance_df = gbt_importance_df.sort(F.col("Importance").desc())
gbt_importance_df \
    .write.mode("overwrite") \
    .saveAsTable("team31_projectdb.model2_feature_importance")

print("Feature importance of the first model was saved.")
