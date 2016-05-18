#!/bin/bash

eval $(docker-machine env machine2)

ME=$(docker-machine ls | grep machine2 | cut -f 3 -d '/' | cut -f 1 -d ':')
echo "IP=$ME"

MASTER=$(docker-machine ls | grep master | cut -f 3 -d '/' | cut -f 1 -d ':')
echo "MASTER_IP=$MASTER"

weave launch $MASTER
eval $(weave env)

echo "Ready on [$ME]"

MACHINE_CID=$(docker ps | grep wdaemachine | awk '{print $1;exit}')
MACHINE=$(weave ps $MACHINE_CID | awk '{print $NF;exit}' | cut -d '/' -f 1)

docker run --rm -it --privileged rsommerard/wdaeemulator $MACHINE
