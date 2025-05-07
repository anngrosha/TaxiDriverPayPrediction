#!/bin/bash

# remove folders to test reproducibility
password=$(head -n 1 secrets/.hive.pass)
hdfs dfs -rm -r -f /user/team31/project/output
hdfs dfs -rm -r -f /user/team31/project/hive

beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team31 -p $password -f sql/db.hql > output/hive_results.txt 2> /dev/null

beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team31 -p $password -f sql/q1.hql
echo "location_id,borough,zone,count" > output/q1.csv 
hdfs dfs -cat project/output/q1/* >> output/q1.csv

beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team31 -p $password -f sql/q2.hql
echo "company,percentage,trips_count" > output/q2.csv 
hdfs dfs -cat project/output/q2/* >> output/q2.csv

beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team31 -p $password -f sql/q3.hql
echo "borough,avg_trip_minutes" > output/q3.csv 
hdfs dfs -cat project/output/q3/* >> output/q3.csv

beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team31 -p $password -f sql/q4.hql
echo "average_trips_count" > output/q4.csv 
hdfs dfs -cat project/output/q4/* >> output/q4.csv

beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team31 -p $password -f sql/q5.hql
echo "temp_range,precipitation_status,trip_count,avg_trip_minutes,avg_trip_miles,avg_trip_price" > output/q5.csv 
hdfs dfs -cat project/output/q5/* >> output/q5.csv

beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team31 -p $password -f sql/q6.hql
echo "trip_datetime,trip_count" > output/q6.csv 
hdfs dfs -cat project/output/q6/* >> output/q6.csv

beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team31 -p $password -f sql/q7.hql
echo "tip_category,trip_count,avg_miles,avg_minutes" > output/q7.csv 
hdfs dfs -cat project/output/q7/* >> output/q7.csv

beeline -u jdbc:hive2://hadoop-03.uni.innopolis.ru:10001 -n team31 -p $password -f sql/q8.hql
echo "hour,average_cost" > output/q8.csv 
hdfs dfs -cat project/output/q8/* >> output/q8.csv