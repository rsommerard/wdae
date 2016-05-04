#!/bin/bash

docker-machine create --driver virtualbox master
docker-machine start master

eval $(docker-machine env master)

ME=$(docker-machine ls | grep master | cut -f 3 -d '/' | cut -f 1 -d ':')
echo "MASTER_IP=$ME"

weave launch
eval $(weave env)

echo "Ready on [$ME]"

docker run --rm -it -e WEAVE_CIDR=10.32.0.42/12 rsommerard/helloweave master
