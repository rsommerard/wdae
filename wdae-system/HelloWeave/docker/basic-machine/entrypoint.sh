#!/bin/bash

MODE=$1
ME=$(ip route | grep "10." | awk '{print $NF;exit}')

echo "MODE: $MODE"
echo "ME: $ME"

./helloweave-1.0/bin/helloweave $MODE $ME
