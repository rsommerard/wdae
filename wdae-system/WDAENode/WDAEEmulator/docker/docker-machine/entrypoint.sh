#!/bin/bash

MACHINE=$1
ME=$(ip route | grep "src 10." | awk '{print $NF;exit}')

echo "MACHINE_IP: $MACHINE"
echo "ME: $ME"

./wdaeemulator-1.0/bin/wdaeemulator $MACHINE
