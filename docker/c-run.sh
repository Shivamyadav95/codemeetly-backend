#!/bin/bash
read -d '' CODE
echo "$CODE" > program.c
gcc program.c -o program 2> errors.txt

if [ $? -ne 0 ]; then
    cat errors.txt
    exit
fi

timeout 3s ./program