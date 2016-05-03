#!/bin/bash

MODE=$1
ME=$(ip route | grep "10." | awk '{print $NF;exit}')

echo "MODE: $MODE"
echo "ME: $ME"

./hello-1.0/bin/hello $MODE $ME
