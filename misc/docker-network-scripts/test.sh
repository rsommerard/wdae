#!/bin/bash

if [ $# == 0 ] ; then
    echo "Usage: test.sh <master_ip>"
    exit 1;
fi

MASTER=$1
echo "MASTER_IP=$MASTER"

echo "Creating machine1..."
docker -H tcp://$MASTER:4000 run -itd --name machine1 --net wdae alpine /bin/sh
sleep 1
echo "Creating machine2..."
docker -H tcp://$MASTER:4000 run -itd --name machine2 --net wdae alpine /bin/sh
sleep 1
echo "Creating machine3..."
docker -H tcp://$MASTER:4000 run -itd --name machine3 --net wdae alpine /bin/sh
sleep 1
echo "Creating machine4..."
docker -H tcp://$MASTER:4000 run -itd --name machine4 --net wdae alpine /bin/sh
sleep 1
echo "Creating machine5..."
docker -H tcp://$MASTER:4000 run -itd --name machine5 --net wdae alpine /bin/sh
sleep 1
echo "Creating machine6..."
docker -H tcp://$MASTER:4000 run -itd --name machine6 --net wdae alpine /bin/sh
sleep 1
echo "Creating machine7..."
docker -H tcp://$MASTER:4000 run -itd --name machine7 --net wdae alpine /bin/sh
sleep 1
echo "Creating machine8..."
docker -H tcp://$MASTER:4000 run -itd --name machine8 --net wdae alpine /bin/sh
sleep 1
echo "Creating machine9..."
docker -H tcp://$MASTER:4000 run -itd --name machine9 --net wdae alpine /bin/sh
sleep 1
echo "Creating machine10..."
docker -H tcp://$MASTER:4000 run -itd --name machine10 --net wdae alpine /bin/sh
