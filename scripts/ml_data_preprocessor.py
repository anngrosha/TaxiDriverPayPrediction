# Import necessary dependencies
import os
import math
from pyspark.sql import SparkSession
from pyspark.sql import functions as F
from pyspark.ml.feature import StringIndexer, OneHotEncoder, VectorAssembler
from pyspark.ml import Pipeline
from pyspark.ml import Transformer
from pyspark.ml.param.shared import HasInputCol, HasOutputCols
from pyspark.ml.util import DefaultParamsReadable, DefaultParamsWritable
from pyspark.ml.linalg import VectorUDT
from pyspark import keyword_only


class CyclicalEncoder(
    Transformer,
    HasInputCol,
    HasOutputCols,
    DefaultParamsReadable,
    DefaultParamsWritable,
):
    # Template: https://csyhuang.github.io/2020/08/01/custom-transformer/
    @keyword_only
    def __init__(self, inputCol=None, outputCols=None):
        super(CyclicalEncoder, self).__init__()
        self._setDefault(inputCol=None, outputCols=None)
        kwargs = self._input_kwargs
        self.set_params(**kwargs)

    @keyword_only
    def set_params(self, inputCol=None, outputCols=None):
        kwargs = self._input_kwargs
        return self._set(**kwargs)

    def get_output_cols(self):
        if self.isSet("outputCols"):
            return self.getOrDefault("outputCols")
        input_col = self.getInputCol()

        return ["{0}_sine".format(input_col), "{0}_cosine".format(input_col)]

    def _transform(self, df):
        input_col = self.getInputCol()
        output_cols = self.get_output_cols()

        return df.withColumn(
            output_cols[0], F.sin(2 * math.pi * F.col(input_col) / 12)
        ).withColumn(
            output_cols[1], F.cos(2 * math.pi * F.col(input_col) / 12),
        )


class DataPreprocessor:

    def __init__(self):
        self.pipelines = {
            "taxi_zone_encode": Pipeline(
                stages=[
                    StringIndexer(
                        inputCol="borough",
                        outputCol="borough_indexed",
                        handleInvalid="keep",
                    ),
                    OneHotEncoder(
                        inputCol="borough_indexed",
                        outputCol="borough_encoded",
                        dropLast=False,
                    ),
                    StringIndexer(
                        inputCol="service_zone",
                        outputCol="service_zone_indexed",
                        handleInvalid="keep",
                    ),
                    OneHotEncoder(
                        inputCol="service_zone_indexed",
                        outputCol="service_zone_encoded",
                        dropLast=False,
                    )
                ]
            ),
            "cyclical_encode": Pipeline(
                stages=[
                    CyclicalEncoder(inputCol="day"),
                    CyclicalEncoder(inputCol="month"),
                ]
            ),
            "license_num_encode": Pipeline(
                stages=[
                    StringIndexer(
                        inputCol="hvfhs_license_num",
                        outputCol="hvfhs_license_num_indexed",
                        handleInvalid="keep",
                    ),
                    OneHotEncoder(
                        inputCol="hvfhs_license_num_indexed",
                        outputCol="hvfhs_license_num_encoded",
                        dropLast=False,
                    ),
                ]
            )
        }

    def _drop_null_cols(self, df):
        return df.drop(
            [
                col for col in df.columns if df.filter(
                    F.col(col).isNull()
                ).count() > 0
            ]
        )

    def _drop_unnecessary_cols(self, df):
        return df.drop(
            "request_datetime",
            "pickup_datetime",
            "dropoff_datetime",
            "request_date",
            "request_time",
            "pu_location_id",
            "do_location_id",
            "location_id_pu",
            "location_id_do",
            "dispatching_base_num",
            "date",
            "station_name",
            "station_address",
            "resolved_address",
            "date_id",
            "day",
            "month",
            "date_w",
            "day_w",
            "month_w",
            "zone",
            "borough",
            "service_zone",
            "borough_indexed",
            "service_zone_indexed",
            "hvfhs_license_num",
            "hvfhs_license_num_indexed",
        )

    def _parse_date(self, df, date_col, prefix=False):
        if prefix:
            return df.withColumn(
                "date_w", F.from_unixtime(date_col).cast("timestamp")
            ).withColumn(
                "day_w", F.dayofmonth("date_w")
            ).withColumn(
                "month_w", F.month("date_w")
            )
        else:
            return df.withColumn(
                "date", F.from_unixtime(date_col).cast("timestamp")
            ).withColumn(
                "day", F.dayofmonth("date")
            ).withColumn(
                "month", F.month("date")
            )

    def process(self, trips_part_df, taxi_zone_df,
                weather_df, show_progress=False):

        # Drop columns with missing values and convert 'N'/'Y' to 0/1
        trips_part = trips_part_df.drop("originating_base_num",
                                        "on_scene_datetime",
                                        "airport_fee")
        flag_columns = ['wav_match_flag', 'wav_request_flag',
                        'shared_match_flag', 'shared_request_flag']
        for column in flag_columns:
            trips_part = trips_part.withColumn(
                column,
                F.when(F.col(column) == 'Y', 1)
                .when(F.col(column) == 'N', 0)
                .otherwise(None))

        weather = weather_df.drop("precipitation_type", "wind_gust", "severe_risk")

        if show_progress:
            print("Removed null columns.")

        # Parse dates for "trips_part" and "weather"
        trips_part = self._parse_date(trips_part, "pickup_datetime")
        weather = self._parse_date(weather, "date_id", prefix=True)

        if show_progress:
            print("Parsed the dates.")

        # Combine both dataframes
        combined_df = trips_part.join(
            F.broadcast(weather),
            (trips_part["day"] == weather["day_w"]) &
            (trips_part["month"] == weather["month_w"]),
            "inner",
        )

        if show_progress:
            print("joined 'trips_part' and 'weather'.")

        # Delete the unnecessary dataframes
        del trips_part, weather

        # Fit the taxi our pipeline
        taxi_zone_encoder = self.pipelines.get(
            "taxi_zone_encode"
        ).fit(taxi_zone_df)
        taxi_zone = taxi_zone_encoder.transform(taxi_zone_df).drop(
            "borough",
            "zone",
            "service_zone",
            "borough_indexed",
            "service_zone_indexed",
        )

        if show_progress:
            print("Encoded taxi zones.")

        # Define the dataframe for pick ups
        pu_taxi_zone_encoded = taxi_zone.select(
            *[F.col(col).alias("{0}_pu".format(col)) for col in taxi_zone.columns]
        )

        # Define the dataframe for drop offs
        do_taxi_zone_encoded = taxi_zone.select(
            *[F.col(col).alias("{0}_do".format(col)) for col in taxi_zone.columns]
        )

        # Add encoded pick up and drop off locations
        combined_df = combined_df.join(
            pu_taxi_zone_encoded,
            (combined_df["pu_location_id"] == 
             pu_taxi_zone_encoded["location_id_pu"]),
            "inner",
        ).join(
            do_taxi_zone_encoded,
            (combined_df["do_location_id"] == 
             do_taxi_zone_encoded["location_id_do"]),
            "inner",
        )

        if show_progress:
            print("Added encoded pick up and drop off locations.")

        # Delete the unnecessary dataframes
        del taxi_zone, pu_taxi_zone_encoded, do_taxi_zone_encoded

        # Fit our cyclical encoder
        cyclical_encoder = self.pipelines.get(
            "cyclical_encode"
        ).fit(combined_df)
        combined_df = cyclical_encoder.transform(combined_df)

        if show_progress:
            print("Encoded days and months using " \
                  "sine and cosine transformations.")

        # Fit our license num encoder
        license_num_encoder = self.pipelines.get(
            "license_num_encode"
        ).fit(combined_df)
        combined_df = license_num_encoder.transform(combined_df)

        if show_progress:
            print("Encoded license numbers.")

        # Exclude non-feature columns
        non_feature_cols = ["trip_id", "driver_pay"]

        combined_df = self._drop_unnecessary_cols(combined_df)

        # Get all feature columns
        feature_cols = [
            col for col in combined_df.columns if col not in non_feature_cols
        ]

        # Assemble all features together
        feature_assembler = VectorAssembler(
            inputCols=feature_cols,
            outputCol="features",
        )

        combined_df = feature_assembler.transform(combined_df)

        return combined_df.select("trip_id", "features", "driver_pay")


def run(command):
    """Helper function to execute shell commands"""
    return os.popen(command).read()


if __name__ == "__main__":

    # Add here your team number teamx
    team = 31

    # location of your Hive database in HDFS
    warehouse = "project/hive/warehouse"

    spark = SparkSession\
        .builder\
        .appName("{} - spark ML".format(team))\
        .master("yarn")\
        .config("hive.metastore.uris",
                "thrift://hadoop-02.uni.innopolis.ru:9883")\
        .config("spark.sql.warehouse.dir", warehouse)\
        .config("spark.sql.avro.compression.codec", "snappy")\
        .enableHiveSupport()\
        .getOrCreate()

    # Ensure that we are using the correct db
    spark.sql("USE team31_projectdb").show()
    spark.sql("SHOW TABLES").show()

    # Read all main Hive tables
    taxi_zones = spark.read.format("avro").table('team31_projectdb.taxi_zones')
    trips_part = spark.read.format("avro").table('team31_projectdb.trips_part')
    weather = spark.read.format("avro").table('team31_projectdb.weather')

    # Define our data preprocessor
    data_preprocessor = DataPreprocessor()

    features_df = data_preprocessor.process(
        trips_part,
        taxi_zones,
        weather,
        show_progress=True
    )

    # Split the dataframe into train and test
    train_df, test_df = features_df.randomSplit([0.7, 0.3], seed=42)

    train_df = train_df\
        .withColumn("features", train_df["features"].cast(VectorUDT()))
    test_df = test_df\
        .withColumn("features", test_df["features"].cast(VectorUDT()))

    # Save training data
    train_df.select("features", "driver_pay")\
        .coalesce(1)\
        .write\
        .mode("overwrite")\
        .parquet("/user/team31/project/data/train")

    run("hdfs dfs -cat /user/team31/project/data/train/*.parquet " \
        "> data/train.parquet")

    # Save test data
    test_df.select("features", "driver_pay")\
        .coalesce(1)\
        .write\
        .mode("overwrite")\
        .parquet("/user/team31/project/data/test")

    run("hdfs dfs -cat /user/team31/project/data/test/*.parquet " \
        "> data/test.parquet")

    print("\nData successfully saved:")
    print("- Distributed training data saved: /user/team31/project/data/train")
    print("- Distributed test data saved: /user/team31/project/data/test")
