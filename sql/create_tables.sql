DROP TABLE IF EXISTS taxi_zones CASCADE;
DROP TABLE IF EXISTS weather CASCADE;
DROP TABLE IF EXISTS trips CASCADE;

CREATE TABLE taxi_zones (
    location_id INT PRIMARY KEY,
    borough VARCHAR(50),
    zone VARCHAR(100),
    service_zone VARCHAR(50)
);

CREATE TABLE weather (
    station_name VARCHAR(50),
    station_address VARCHAR(100),
    resolved_address VARCHAR(100),
    date_id DATE PRIMARY KEY,
    temperature FLOAT,
    feels_like FLOAT,
    dew_point FLOAT,
    humidity FLOAT,
    precipitation FLOAT,
    precipitation_prob INT,
    precipitation_type VARCHAR(50),
    snow FLOAT,
    snow_depth FLOAT,
    wind_gust FLOAT,
    wind_speed FLOAT,
    wind_direction FLOAT,
    sea_level_pressure FLOAT,
    cloud_cover FLOAT,
    visibility FLOAT,
    uv_index INT,
    severe_risk FLOAT
);

CREATE TABLE trips (
    trip_id BIGSERIAL PRIMARY KEY,
    hvfhs_license_num VARCHAR(6),
    dispatching_base_num VARCHAR(6),
    originating_base_num VARCHAR(6),
    request_datetime TIMESTAMP,
    on_scene_datetime TIMESTAMP,
    pickup_datetime TIMESTAMP,
    dropoff_datetime TIMESTAMP,
    pu_location_id INT,
    do_location_id INT,
    trip_miles FLOAT,
    trip_time INT,
    base_passenger_fare FLOAT,
    tolls FLOAT,
    bcf FLOAT,
    sales_tax FLOAT,
    congestion_surcharge FLOAT,
    airport_fee FLOAT,
    tips FLOAT,
    driver_pay FLOAT,
    shared_request_flag CHAR(1),
    shared_match_flag CHAR(1),
    wav_request_flag CHAR(1),
    wav_match_flag CHAR(1),
    request_date DATE,
    request_time TIME
);

ALTER TABLE trips DROP CONSTRAINT IF EXISTS fk_trips_pu_location;
ALTER TABLE trips ADD CONSTRAINT fk_trips_pu_location FOREIGN KEY (pu_location_id) REFERENCES taxi_zones(location_id);

ALTER TABLE trips DROP CONSTRAINT IF EXISTS fk_trips_do_location;
ALTER TABLE trips ADD CONSTRAINT fk_trips_do_location FOREIGN KEY (do_location_id) REFERENCES taxi_zones(location_id);

ALTER TABLE trips DROP CONSTRAINT IF EXISTS fk_trips_weather_date;
ALTER TABLE trips ADD CONSTRAINT fk_trips_weather_date FOREIGN KEY (request_date) REFERENCES weather(date_id);
