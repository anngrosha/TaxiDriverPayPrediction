import pyarrow.parquet as pq
import os

num_of_rows = 0
for month in ['01', '02', '03']:
    parquet_file = os.path.join("data", f"trips_{month}.parquet")
    if os.path.exists(parquet_file):
        print(f"Processing {parquet_file}...")

        save_file_path = os.path.join("data", f"trips_{month}.csv")
        first_batch = True

        for batch in pq.read_table(parquet_file).to_batches(max_chunksize=2000):
            if num_of_rows >= 2_450_000:
                break
            df = batch.to_pandas()

            df = df.dropna(subset=['request_datetime'])
            df = df[df['request_datetime'] >= '2021-01-01']
            df['request_date'] = df['request_datetime'].dt.date
            df['request_time'] = df['request_datetime'].dt.time
            df.drop('access_a_ride_flag', axis=1, inplace=True)

            df.to_csv(
                save_file_path,
                mode='w' if first_batch else 'a',
                header=first_batch,
                index=False
            )
            first_batch = False
            num_of_rows += len(df)