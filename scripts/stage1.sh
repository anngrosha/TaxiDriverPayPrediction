#!/bin/bash

echo "PostgreSQL DB building"
python3 ./scripts/build_projectdb.py

echo "Loading to HDFS"
password=$(head -n 1 secrets/.psql.pass)
hdfs dfs -rm -r project/warehouse

echo "Load taxi zones"
sqoop import --connect jdbc:postgresql://hadoop-04.uni.innopolis.ru/team31_projectdb --username team31 --password $password --compression-codec=snappy --compress --as-avrodatafile --warehouse-dir=/user/team31/project/warehouse --m 1 --table taxi_zones
echo "Load weather"
sqoop import --connect jdbc:postgresql://hadoop-04.uni.innopolis.ru/team31_projectdb --username team31 --password $password --compression-codec=snappy --compress --as-avrodatafile --warehouse-dir=/user/team31/project/warehouse --m 1 --table weather
echo "Load taxi trips"
sqoop import --connect jdbc:postgresql://hadoop-04.uni.innopolis.ru/team31_projectdb --username team31 --password $password --compression-codec=snappy --compress --as-avrodatafile --warehouse-dir=/user/team31/project/warehouse --m 1 --table trips

rm -rf ./output
mkdir ./output
mv *.avsc output/
mv *.java output/

hdfs dfs -mkdir -p project/warehouse/avsc
hdfs dfs -put output/*.avsc project/warehouse/avsc
# hdfs dfs -ls project/warehouse/avsc

echo "Stage 1 is complete!"