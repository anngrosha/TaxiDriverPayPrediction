#!/bin/bash
echo "Preprocessing has started"

echo "Data collection has started"
chmod +x ./scripts/data_collection.sh
./scripts/data_collection.sh

echo "Venv setup"
python3 -m venv .venv
source .venv/bin/activate
pip install --upgrade pip
pip install -r requirements.txt

python3 ./scripts/preprocess.py

echo "All files are loaded from Yandex disk"