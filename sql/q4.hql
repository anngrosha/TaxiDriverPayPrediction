USE team31_projectdb;
DROP TABLE IF EXISTS q4_results;

CREATE EXTERNAL TABLE q4_results( 
    average_trips_count float
) 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
LOCATION 'project/hive/warehouse/q4';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q4_results
SELECT 
    (COUNT(*) / COUNT(DISTINCT(FROM_UNIXTIME(cast(request_date/1000 as int))))) as average_trips_count
FROM 
    trips_part;

INSERT OVERWRITE DIRECTORY 'project/output/q4' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q4_results;