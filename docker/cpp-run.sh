#!/bin/bash
read -d '' CODE
echo "$CODE" > program.cpp
g++ program.cpp -o program 2> errors.txt

if [ $? -ne 0 ]; then
    cat errors.txt
    exit
fi

timeout 3s ./program