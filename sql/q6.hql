USE team31_projectdb;
DROP TABLE IF EXISTS q6_results;

CREATE EXTERNAL TABLE q6_results( 
    trip_datetime timestamp,
    trip_count int
) 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
LOCATION 'project/hive/warehouse/q6';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q6_results
SELECT 
    FROM_UNIXTIME(cast(request_datetime/1000 as int)) as trip_datetime,
    COUNT(*) AS trip_count
FROM 
    trips_part
GROUP BY 
    FROM_UNIXTIME(cast(request_datetime/1000 as int))
ORDER BY 
    trip_datetime;

INSERT OVERWRITE DIRECTORY 'project/output/q6' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q6_results;





