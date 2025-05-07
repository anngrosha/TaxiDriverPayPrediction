# Import necessary dependencies
from pyspark.sql import SparkSession

# Add here your team number teamx
team = 31

# location of your Hive database in HDFS
warehouse = "project/hive/warehouse"

spark = SparkSession.builder\
        .appName("{} - spark ML".format(team))\
        .master("yarn")\
        .config("hive.metastore.uris", "thrift://hadoop-02.uni.innopolis.ru:9883")\
        .config("spark.sql.warehouse.dir", warehouse)\
        .config("spark.sql.avro.compression.codec", "snappy")\
        .enableHiveSupport()\
        .getOrCreate()

# List all tables
spark.sql("USE team31_projectdb").show()
spark.sql("SHOW TABLES").show()

# Execute some queries
spark.sql("""
    SELECT * FROM taxi_zones LIMIT 10;
""").show()

spark.sql("""
    SELECT * FROM trips LIMIT 1;
""").show(vertical=True, truncate=False)

spark.sql("""
    SELECT * FROM trips_part LIMIT 1;
""").show(vertical=True, truncate=False)

spark.sql("""
    SELECT * FROM weather LIMIT 1;
""").show(vertical=True, truncate=False)