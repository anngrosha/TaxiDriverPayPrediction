SHOW DATABASES;
USE team31_projectdb;

DROP TABLE IF EXISTS weather;
DROP TABLE IF EXISTS taxi_zones;
DROP TABLE IF EXISTS trips;
DROP TABLE IF EXISTS trips_part;

CREATE EXTERNAL TABLE weather STORED AS AVRO LOCATION '/user/team31/project/warehouse/weather' TBLPROPERTIES ('avro.schema.url'='/user/team31/project/warehouse/avsc/weather.avsc');
select * from weather limit 2;

CREATE EXTERNAL TABLE taxi_zones STORED AS AVRO LOCATION '/user/team31/project/warehouse/taxi_zones' TBLPROPERTIES ('avro.schema.url'='/user/team31/project/warehouse/avsc/taxi_zones.avsc');
select * from taxi_zones limit 1;

CREATE EXTERNAL TABLE trips STORED AS AVRO LOCATION '/user/team31/project/warehouse/trips' TBLPROPERTIES ('avro.schema.url'='/user/team31/project/warehouse/avsc/trips.avsc');
select * from trips limit 1;


CREATE EXTERNAL TABLE trips_part(
    trip_id bigint,
    hvfhs_license_num string,
    originating_base_num string,
    request_datetime bigint,
    on_scene_datetime bigint,
    pickup_datetime bigint,
    dropoff_datetime bigint,
    pu_location_id int,
    do_location_id int,
    trip_miles float,
    trip_time int,
    base_passenger_fare float,
    tolls float,
    bcf float,
    sales_tax float,
    congestion_surcharge float,
    airport_fee float,
    tips float,
    driver_pay float,
    shared_request_flag varchar(1),
    shared_match_flag varchar(1),
    wav_request_flag varchar(1),
    wav_match_flag varchar(1),
    request_date bigint,
    request_time bigint
) 
PARTITIONED BY (dispatching_base_num STRING) CLUSTERED BY (pu_location_id, do_location_id) INTO 8 BUCKETS STORED AS AVRO LOCATION 'project/hive/warehouse/trips_part' TBLPROPERTIES ('AVRO.COMPRESS'='SNAPPY');

SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;


INSERT OVERWRITE TABLE trips_part PARTITION(dispatching_base_num) 
SELECT trip_id, hvfhs_license_num, originating_base_num, request_datetime, on_scene_datetime, pickup_datetime,
    dropoff_datetime, pu_location_id, do_location_id, trip_miles, trip_time, base_passenger_fare, tolls,
    bcf, sales_tax, congestion_surcharge, airport_fee, tips, driver_pay, shared_request_flag, shared_match_flag,
    wav_request_flag, wav_match_flag, request_date, request_time, dispatching_base_num  
FROM trips;

SELECT * FROM trips_part LIMIT 2;
SHOW PARTITIONS trips_part;

!q