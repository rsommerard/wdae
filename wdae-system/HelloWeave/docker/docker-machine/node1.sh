#!/bin/bash

docker-machine create --driver virtualbox node1
docker-machine start node1

eval $(docker-machine env node1)

ME=$(docker-machine ls | grep node1 | cut -f 3 -d '/' | cut -f 1 -d ':')
echo "IP=$ME"

MASTER=$(docker-machine ls | grep master | cut -f 3 -d '/' | cut -f 1 -d ':')
echo "MASTER_IP=$MASTER"

weave launch $MASTER
eval $(weave env)

echo "Ready on [$ME]"

docker run --rm -it rsommerard/helloweave node
