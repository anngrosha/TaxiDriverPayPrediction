USE team31_projectdb;
DROP TABLE IF EXISTS q7_results;

CREATE EXTERNAL TABLE q7_results( 
    tip_category string,
    trip_count int,
    avg_miles float,
    avg_minutes float
) 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
LOCATION 'project/hive/warehouse/q7';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q7_results
SELECT 
  CASE 
    WHEN tips/base_passenger_fare > 0.25 THEN 'High Tip'
    WHEN tips/base_passenger_fare > 0.15 THEN 'Medium Tip'
    WHEN tips > 0 THEN 'Low Tip'
    ELSE 'No Tip'
  END as tip_category,
  COUNT(*) AS trip_count,
  AVG(trip_miles) AS avg_miles,
  AVG(trip_time)/60 AS avg_minutes
FROM 
  trips_part
WHERE 
  base_passenger_fare > 0
GROUP BY 
  CASE 
    WHEN tips/base_passenger_fare > 0.25 THEN 'High Tip'
    WHEN tips/base_passenger_fare > 0.15 THEN 'Medium Tip'
    WHEN tips > 0 THEN 'Low Tip'
    ELSE 'No Tip'
  END;

INSERT OVERWRITE DIRECTORY 'project/output/q7' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q7_results;