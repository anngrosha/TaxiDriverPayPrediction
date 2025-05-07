USE team31_projectdb;
DROP TABLE IF EXISTS q3_results;

CREATE EXTERNAL TABLE q3_results( 
    borough string,
    avg_trip_minutes float
) 
ROW FORMAT DELIMITED 
FIELDS TERMINATED BY ',' 
LOCATION 'project/hive/warehouse/q3';

SET hive.resultset.use.unique.column.names = false;

INSERT INTO q3_results
SELECT 
  tz.borough as borough,
  AVG(t.trip_time/60) as avg_trip_minutes
FROM 
  trips_part t
JOIN 
  taxi_zones tz ON t.pu_location_id = tz.location_id
GROUP BY 
  tz.borough
ORDER BY 
  avg_trip_minutes DESC;

INSERT OVERWRITE DIRECTORY 'project/output/q3' 
ROW FORMAT DELIMITED FIELDS 
TERMINATED BY ',' 
SELECT * FROM q3_results;







most expensive trips:
SELECT 
    HOUR(FROM_UNIXTIME(cast(request_time/1000 as int))) AS hour,
    AVG(base_passenger_fare + tolls + bcf + sales_tax + congestion_surcharge + airport_fee) AS average_cost
FROM trips_part
GROUP BY HOUR(FROM_UNIXTIME(cast(request_time/1000 as int)))
ORDER BY hour DESC;

Average Trip Duration by Borough
SELECT 
  tz.borough as borough,
  AVG(t.trip_time/60) as avg_trip_minutes
FROM 
  trips_part t
JOIN 
  taxi_zones tz ON t.pu_location_id = tz.location_id
GROUP BY 
  tz.borough
ORDER BY 
  avg_trip_minutes DESC;
+----------------+---------------------+
|   tz.borough   |  avg_trip_minutes   |
+----------------+---------------------+
| EWR            | 35.914827586206904  |
| Queens         | 16.543948657519326  |
| Brooklyn       | 15.885709782073711  |
| Manhattan      | 15.809746598317444  |
| Unknown        | 15.497591522157995  |
| Staten Island  | 14.70611305675289   |
| Bronx          | 14.186824513548702  |
+----------------+---------------------+

  
airports
SELECT 
  tz_drop.Zone as dropoff_zone,
  COUNT(*) as trip_count,
  AVG(base_passenger_fare + tolls + tips) as avg_total_revenue,
  SUM(base_passenger_fare + tolls + tips) as total_revenue
FROM 
  trips_part t
JOIN 
  taxi_zones tz_pick ON t.pu_Location_id = tz_pick.location_id
JOIN 
  taxi_zones tz_drop ON t.do_Location_id = tz_drop.location_id
WHERE 
  tz_drop.Zone IN ('JFK Airport', 'LaGuardia Airport', 'Newark Airport')
GROUP BY 
  tz_drop.Zone
ORDER BY 
  total_revenue DESC;
  
  
average driver pay
SELECT 
  HOUR(FROM_UNIXTIME(request_datetime)) AS hour_of_day,
  AVG(driver_pay) AS avg_driver_pay,
  AVG(driver_pay/(trip_time/3600)) AS avg_hourly_earnings
FROM 
  trips_part
GROUP BY 
  HOUR(FROM_UNIXTIME(request_datetime))
ORDER BY 
  hour_of_day;
  
