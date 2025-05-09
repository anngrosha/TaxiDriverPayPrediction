USE team31_projectdb;
DROP TABLE IF EXISTS q2_results;

CREATE EXTERNAL TABLE q2_results( 
    company string,
    percentage float,
    trips_count int
) 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
LOCATION 'project/hive/warehouse/q2';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q2_results
SELECT 
    CASE hvfhs_license_num
        WHEN 'HV0003' THEN 'Uber'
        WHEN 'HV0004' THEN 'Via'
        WHEN 'HV0005' THEN 'Lyft'
        ELSE 'Other'
    END AS company,
    ROUND(COUNT(*) * 100.0 / subq.total_count, 2) AS percentage,
    COUNT(*) AS trips_count 
FROM trips_part 
JOIN (SELECT COUNT(*) AS total_count FROM trips_part) subq ON 1=1
GROUP BY hvfhs_license_num, subq.total_count
ORDER BY trips_count DESC;

INSERT OVERWRITE DIRECTORY 'project/output/q2' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q2_results;