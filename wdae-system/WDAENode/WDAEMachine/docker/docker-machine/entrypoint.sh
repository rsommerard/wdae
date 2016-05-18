#!/bin/bash

ME=$(ip route | grep "src 10." | awk '{print $NF;exit}')

echo "ME: $ME"

./wdaemachine-1.0/bin/wdaemachine $ME
