USE team31_projectdb;
DROP TABLE IF EXISTS q1_results;

CREATE EXTERNAL TABLE q1_results( 
    location_id int,
    borough string,
    zone string,
    count int
) 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
LOCATION 'project/hive/warehouse/q1';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q1_results
SELECT 
    MAX(tz.location_id) AS location_id,
    tz.borough AS borough,
    tz.zone AS zone,
    COUNT(*) AS trips_count
FROM trips_part tp
JOIN taxi_zones tz ON tp.do_location_id = tz.location_id
GROUP BY tz.borough, tz.zone
ORDER BY trips_count DESC
LIMIT 20;

INSERT OVERWRITE DIRECTORY 'project/output/q1' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q1_results;