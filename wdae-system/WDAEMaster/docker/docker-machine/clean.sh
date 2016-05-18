#!/bin/bash

rm *.zip

# Clean master
eval $(docker-machine env master)

# Kill running containers
docker kill $(docker ps | grep "rsommerard/wdaemaster" | awk '{ print $1; }')
docker kill $(docker ps | grep "weaveworks/plugin" | awk '{ print $1; }')
docker kill $(docker ps | grep "weaveworks/weaveexec" | awk '{ print $1; }')
docker kill $(docker ps | grep "weaveworks/weave" | awk '{ print $1; }')

# Delete stopped containers
docker rm -f $(docker ps | grep "rsommerard/wdaemaster" | awk '{ print $1; }')
docker rm -f $(docker ps | grep "weaveworks/plugin" | awk '{ print $1; }')
docker rm -f $(docker ps | grep "weaveworks/weaveexec" | awk '{ print $1; }')
docker rm -f $(docker ps | grep "weaveworks/weave" | awk '{ print $1; }')

# Delete all 'untagged/dangling' (<none>) images
docker rmi -f $(docker images -q -f dangling=true)

docker rmi -f $(docker images | grep "rsommerard/wdaemaster" | awk '{ print $3; }')
docker rmi -f $(docker images | grep "weaveworks/plugin" | awk '{ print $3; }')
docker rmi -f $(docker images | grep "weaveworks/weaveexec" | awk '{ print $3; }')
docker rmi -f $(docker images | grep "weaveworks/weave" | awk '{ print $3; }')
