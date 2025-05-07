import psycopg2 as psql
from pprint import pprint
import os

file = os.path.join("secrets", ".psql.pass")
with open(file, "r") as file:
    password = file.read().rstrip()

conn_string = f"host=hadoop-04.uni.innopolis.ru port=5432 user=team31 \
    dbname=team31_projectdb password={password}"


with psql.connect(conn_string) as conn:
    cur = conn.cursor()
    with open(os.path.join("sql", "create_tables.sql")) as file:
        content = file.read()
        cur.execute(content)
    conn.commit()

    with open(os.path.join("sql", "import_data.sql")) as file:
        commands = file.readlines()
        with open(os.path.join("data", "weather.csv"), "r") as weather:
            cur.copy_expert(commands[0], weather)
        with open(os.path.join("data", "taxi_zones.csv"), "r") as taxi_zones:
            cur.copy_expert(commands[1], taxi_zones)
        for month in ['01']:
            with open(os.path.join("data", f"trips_{month}.csv")) as trips:
                cur.copy_expert(commands[2], trips)

    conn.commit()
    
    # Testing:
    # pprint(conn)
    # cur = conn.cursor()
    # # Read the sql commands from the file
    # with open(os.path.join("sql", "test_database.sql")) as file:
    #     commands = file.readlines()
    #     # print(commands)
    #     for command in commands:
    #         cur.execute(command)
    #         # Read all records and print them
    #         pprint(cur.fetchall())