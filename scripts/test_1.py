# Import necessary dependencies
from pyspark.sql import SparkSession
from pyspark.sql import functions as F
from pyspark.ml import Pipeline
from pyspark.sql import DataFrame
from pyspark.ml.linalg import Vectors, VectorUDT
from pyspark.sql.functions import udf
from pyspark.ml.regression import LinearRegression, GBTRegressor, LinearRegressionModel, GBTRegressionModel
from pyspark.ml.tuning import ParamGridBuilder, CrossValidator
from pyspark.ml.evaluation import RegressionEvaluator
from pyspark.ml.feature import StandardScaler
from pyspark.sql.types import *
from pyspark.sql import Row
import pyspark.pandas as ps
import pandas as pd
import os


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

# Load the best models
best_lr_model = LinearRegressionModel.load("/user/team31/project/models/model1")
best_gbt_model = GBTRegressionModel.load("/user/team31/project/models/model2")

#best_lr_model = spark.sparkContext.broadcast(best_lr_model)
#best_gbt_model = spark.sparkContext.broadcast(best_gbt_model)

print("Models are read.====================================================================================")

from pyspark import SparkContext

def test_spark_serialization(obj):
    try:
        # Create a dummy RDD with the object
        spark.sparkContext.parallelize([obj]).map(lambda x: x).collect()
        return True
    except Exception as e:
        print(f"Spark serialization failed: {str(e)}")
        return False

# Test your models
print("LR Model Spark-serializable:", test_spark_serialization(best_lr_model), "============================================================================")
print("GBT Model Spark-serializable:", test_spark_serialization(best_gbt_model), "============================================================================")

from pyspark.sql.functions import lit

# First transform the test data with each model
lr_predictions = best_lr_model.transform(test_df)
gbt_predictions = best_gbt_model.transform(test_df)

# Register the transformed DataFrames
lr_predictions.createOrReplaceTempView("lr_predictions")
gbt_predictions.createOrReplaceTempView("gbt_predictions")

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

# Save models' predictions
#predictions = best_lr_model.transform(test_df)
#predictions.show()

#predictions.select("driver_pay", "driver_pay_pred")\
#    .coalesce(1)\
#    .write\
#    .mode("overwrite")\
#    .format("csv")\
#    .option("sep", ",")\
#    .option("header", "true")\
#    .save("/user/team31/project/output/model1_predictions.csv")

# Run it from root directory of the repository
#run("hdfs dfs -cat project/output/model1_predictions.csv/*.csv > output/model1_predictions.csv")

# Do the same for GBT
#predictions = best_gbt_model.transform(test_df)
#predictions.show()

#predictions.select("driver_pay", "driver_pay_pred")\
#    .coalesce(1)\
#    .write\
#    .mode("overwrite")\
#    .format("csv")\
#    .option("sep", ",")\
#    .option("header", "true")\
#    .save("/user/team31/project/output/model2_predictions.csv")

# Run it from root directory of the repository
#run("hdfs dfs -cat project/output/model2_predictions.csv/*.csv > output/model2_predictions.csv")

print("Predictions are saved.====================================================================================")

# Delete an unnecessary dataframe
#del predictions


# Simple evaluation function for the report table
def evaluate_model(model, test_data, model_name):
    predictions = model.transform(test_data)

    metrics = {
        "Model": str(model),
        "RMSE": RegressionEvaluator(metricName="rmse", labelCol="driver_pay", predictionCol="driver_pay_pred").evaluate(predictions),
        "MAE": RegressionEvaluator(metricName="mae", labelCol="driver_pay", predictionCol="driver_pay_pred").evaluate(predictions),
        "R2": RegressionEvaluator(metricName="r2", labelCol="driver_pay", predictionCol="driver_pay_pred").evaluate(predictions),
    }
    return metrics


def evaluate_model(model, test_data, model_name):
    predictions = model.transform(test_data)

    metrics = [
        str(model),
        RegressionEvaluator(metricName="rmse", labelCol="driver_pay", predictionCol="driver_pay_pred").evaluate(predictions),
        RegressionEvaluator(metricName="mae", labelCol="driver_pay", predictionCol="driver_pay_pred").evaluate(predictions),
        RegressionEvaluator(metricName="r2", labelCol="driver_pay", predictionCol="driver_pay_pred").evaluate(predictions),
    ]
    return metrics


# Create data frame to report performance of the models
#models = [
#    evaluate_model(best_lr_model, test_df, "LinearRegression"),
#    evaluate_model(best_gbt_model, test_df, "GBTRegression"),
#]

#print("=====================================================================================")
#print(models)

#temp_df = spark.createDataFrame(models, ["model", "RMSE", "MAE", "R2"])
#temp_df.createOrReplaceTempView("temp_results")

# Then create your final DataFrame from the SQL query
#spark.sql("SELECT * FROM temp_results").show()
#results_df = spark.sql("SELECT * FROM temp_results")
#models_rdd = spark.sparkContext.parallelize(models)
#results_df = spark.createDataFrame(models_rdd, ["model", "RMSE", "MAE", "R2"])

# Create Pandas DataFrame
#pd_df = pd.DataFrame(models)
#print(pd_df.head())

# Convert to Spark DataFrame
#results_df = ps.from_pandas(pd_df)

#results_df.to_csv(
#    "/user/team31/project/output/evaluation.csv",
#    sep=",",
#    header=True,
#    num_files=1,
#)

#temp = list(map(list, models.items()))
#results_df = spark.createDataFrame(models, ["model", "RMSE", "MAE", "R2"])

# Evaluate with string names
#metrics = [
#    evaluate_model(best_lr_model, test_df, "LinearRegression"),
#    evaluate_model(best_gbt_model, test_df, "GBTRegression"),
#]


#schema = StructType([
#    StructField("Model", StringType()),
#    StructField("RMSE", DoubleType()),
#    StructField("MAE", DoubleType()),
#    StructField("R2", DoubleType())
#])

#results_df = spark.createDataFrame(
#    [Row(**metric) for metric in metrics],
#    schema=schema,
#)

#results_df = spark.createDataFrame(metrics).select("Model", "RMSE", "MAE", "R2")
temp='''results_df.show()

# Save it to HDFS
results_df.coalesce(1)\
    .write\
    .mode("overwrite")\
    .format("csv")\
    .option("sep", ",")\
    .option("header", "true")\
    .save("/user/team31/project/output/evaluation.csv")'''

# Run it from root directory of the repository
#run("hdfs dfs -cat project/output/evaluation.csv/*.csv > output/evaluation.csv")
#print("Evaluation is saved.")