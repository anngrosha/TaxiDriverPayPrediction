rm -rf data/

url="https://disk.yandex.ru/d/Whi5GEVGPvZN9Q"

echo "Loading from Yandex disk..."
mkdir ./data
wget "$(yadisk-direct $url)" -O data/data.zip

unzip data/data.zip -d data/
cp data/BigData/* data/
rm -rf data/BigData
rm data/data.zip
mv "data/nyc 2021-01-01 to 2021-12-31.csv" "data/weather.csv"
mv "data/taxi_zone_lookup.csv" "data/taxi_zones.csv"
mv "data/fhvhv_tripdata_2021-01.parquet" "data/trips_01.parquet"
mv "data/fhvhv_tripdata_2021-02.parquet" "data/trips_02.parquet"
