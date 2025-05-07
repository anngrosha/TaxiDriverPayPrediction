#!/bin/bash

# Export Hadoop configuration
export HADOOP_CONF_DIR=/etc/hadoop/conf

# Run the preprocessing
spark-submit --master yarn ./scripts/ml_data_preprocessor.py --train_hdfs "project/data/train" --test_hdfs "project/data/test" --local_output_dir "data"

# Run the modeling stage
spark-submit --master yarn ./scripts/model_fine_tuner.py