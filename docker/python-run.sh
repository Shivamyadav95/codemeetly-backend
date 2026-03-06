#!/bin/bash
read -d '' CODE
echo "$CODE" > script.py
timeout 3s python3 script.py