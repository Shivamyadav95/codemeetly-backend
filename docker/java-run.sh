#!/bin/bash
read -d '' CODE
echo "$CODE" > Main.java
javac Main.java 2> errors.txt

if [ $? -ne 0 ]; then
    cat errors.txt
    exit
fi

timeout 3s java Main