#!/bin/bash
read -d '' CODE
echo "$CODE" > script.js
timeout 3s node script.js