# Import necessary dependencies
from pyspark.sql import SparkSession
from pyspark.sql import functions as F
from pyspark.ml import Pipeline
from pyspark.sql import DataFrame
from pyspark.ml.linalg import Vectors, VectorUDT
from pyspark.sql.functions import udf
from pyspark.ml.regression import LinearRegression, GBTRegressor
from pyspark.ml.tuning import ParamGridBuilder, CrossValidator
from pyspark.ml.evaluation import RegressionEvaluator
from pyspark.ml.feature import StandardScaler
from pyspark.sql.types import *
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

#train_df = spark.read.json(train_hdfs_path)
#test_df = spark.read.json(test_hdfs_path)

print("Files were readed.")
train_df.limit(1).show(vertical=True, truncate=False)
test_df.limit(1).show(vertical=True, truncate=False)

# Create UDF to convert struct to sparse vector
def struct_to_vector(struct_col):
    return Vectors.sparse(
        int(struct_col['size']), 
        [int(i) for i in struct_col['indices']],
        [float(v) for v in struct_col['values']]
    )


vector_udf = udf(struct_to_vector, VectorUDT())

# Apply conversion
#train_df = train_df.withColumn("features", vector_udf("features"))
#test_df = test_df.withColumn("features", vector_udf("features"))

print("Files were converted.")

#train_df.limit(1).show(vertical=True, truncate=False)
#test_df.limit(1).show(vertical=True, truncate=False)

# Standard scaler for features
standard_scaler = StandardScaler(
    inputCol="features",
    outputCol="features_standardazied",
    withStd=True,
    withMean=True,
)

standard_scaler = standard_scaler.fit(train_df)

print("Scaler fitted.")

# Scale our datasets
train_df = standard_scaler.transform(train_df)
test_df = standard_scaler.transform(test_df)

print("Scaled dataframes.")