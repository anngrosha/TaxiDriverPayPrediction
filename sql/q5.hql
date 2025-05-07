USE team31_projectdb;
DROP TABLE IF EXISTS q5_results;

CREATE EXTERNAL TABLE q5_results( 
    temp_range string,
    precipitation_status string,
    trip_count int,
    avg_trip_minutes float,
    avg_trip_miles float,
    avg_trip_price float
) 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
LOCATION 'project/hive/warehouse/q5';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q5_results
SELECT 
  CASE 
    WHEN w.temperature < 0 THEN 'Freezing (< 0°)'
    WHEN w.temperature BETWEEN 0 AND 5 THEN 'Cold (< 5°)'
    WHEN w.temperature > 5 THEN 'Warm (> 5°)'
  END AS temp_range,
  CASE 
    WHEN w.precipitation >= 10 THEN 'Very High Precipitation'
    WHEN w.precipitation >= 5 THEN 'High Precipitation'
    WHEN w.precipitation >= 2 THEN 'Average Precipitation'
    WHEN w.precipitation > 0 THEN 'Small Precipitation'
    ELSE 'Dry'
  END AS precipitation_status,
  COUNT(*) AS trip_count,
  AVG(t.trip_time)/60 AS avg_trip_minutes,
  AVG(t.trip_miles) AS avg_trip_miles,
  AVG(t.base_passenger_fare + t.tolls + t.bcf + t.sales_tax + t.congestion_surcharge + t.airport_fee) AS avg_trip_price
FROM 
  trips_part t
JOIN 
  weather w ON t.request_date = w.date_id
GROUP BY 
  CASE 
    WHEN w.temperature < 0 THEN 'Freezing (< 0°)'
    WHEN w.temperature BETWEEN 0 AND 5 THEN 'Cold (< 5°)'
    WHEN w.temperature > 5 THEN 'Warm (> 5°)'
  END,
  CASE 
    WHEN w.precipitation >= 10 THEN 'Very High Precipitation'
    WHEN w.precipitation >= 5 THEN 'High Precipitation'
    WHEN w.precipitation >= 2 THEN 'Average Precipitation'
    WHEN w.precipitation > 0 THEN 'Small Precipitation'
    ELSE 'Dry'
  END
ORDER BY 
  temp_range, precipitation_status;

INSERT OVERWRITE DIRECTORY 'project/output/q5' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q5_results;




