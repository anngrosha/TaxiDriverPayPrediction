# Uber Driver Pay Prediction - Big Data Pipeline

## Project Description
This project implements an end-to-end big data pipeline to analyze and predict Uber driver payments in New York City using 2021 trip data. The system ingests data from multiple sources, processes it through a distributed computing architecture, and provides both analytical insights and machine learning predictions.

## Dataset
The project uses three primary datasets from the NYC Taxi and Limousine Commission:
1. **Trip Records**: 2.4 million Uber trips from January 2021 (Parquet format)
2. **Taxi Zones**: Geographic boundaries and attributes for 265 NYC zones (CSV)
3. **Weather Data**: Daily weather records for NYC in 2021 (CSV)

Dataset Source: [Kaggle - Uber NYC Trip Data](https://www.kaggle.com/datasets/shuhengmo/uber-nyc-forhire-vehicles-trip-data-2021)

## Pipeline Architecture
The project follows a four-stage architecture:

1. **Data Ingestion**: PostgreSQL → HDFS using Sqoop
2. **Data Preparation**: HDFS → Hive with partitioning and bucketing
3. **Data Analysis**: Spark SQL and MLlib for EDA and modeling
4. **Data Presentation**: Apache Superset dashboard

## Installation & Usage

### Prerequisites
- Hadoop cluster (tested on CDH 6.3.2)
- Python 3.8+
- Java 8
- Spark 3.1.1
- Hive 2.1.1
- PostgreSQL 12

### Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/anngrosha/UberDriverPayPrediction
   cd UberDriverPayPrediction
   ```
2. Run the pipeline:
   ```bash
   ./main.sh
   ```

## Contributors
- [Anastasia Pichugina](a.pichugina@innopolis.university) 
- [Arthur Gubaidullin](a.gubaidullin@innopolis.university)
- [Israel Adewuyi](i.adewuyi@innopolis.university)
- [Anna Gromova](an.gromova@innopolis.university)
