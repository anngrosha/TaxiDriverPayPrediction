USE team31_projectdb;
DROP TABLE IF EXISTS q8_results;

CREATE EXTERNAL TABLE q8_results( 
    hour int,
    average_cost float
) 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
LOCATION 'project/hive/warehouse/q8';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q8_results
SELECT 
    HOUR(FROM_UNIXTIME(cast(request_time/1000 as int))) AS hour,
    AVG(base_passenger_fare + tolls + bcf + sales_tax + congestion_surcharge + airport_fee) AS average_cost
FROM trips_part
GROUP BY HOUR(FROM_UNIXTIME(cast(request_time/1000 as int)))
ORDER BY hour DESC;


INSERT OVERWRITE DIRECTORY 'project/output/q8' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q8_results;