import os
import pyarrow.parquet as pq

ROWS_NUM: int = 0

for month in ['01', ]:
    parquet_file = os.path.join("data", f"trips_{month}.parquet")
    if os.path.exists(parquet_file):
        print(f"Processing {parquet_file}...")

        save_file_path = os.path.join("data", f"trips_{month}.csv")

        table = pq.read_table(parquet_file)
        print("table is read")
        batches = table.to_batches(max_chunksize=2000)
        print("batched")

        FIRST_BATCH: bool = True
        for batch in batches:
            if ROWS_NUM >= 2_450_000:
                break
            df = batch.to_pandas()

            df = df.dropna(subset=['request_datetime'])
            df = df[df['request_datetime'] >= '2021-01-01']
            df['request_date'] = df['request_datetime'].dt.date
            df['request_time'] = df['request_datetime'].dt.time
            df.drop('access_a_ride_flag', axis=1, inplace=True)

            df.to_csv(
                save_file_path,
                mode='w' if FIRST_BATCH else 'a',
                header=FIRST_BATCH,
                index=False
            )
            FIRST_BATCH = False
            ROWS_NUM += len(df)
