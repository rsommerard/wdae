#!/bin/bash

eval $(docker-machine env machine1)

docker kill $(docker ps | grep "rsommerard/wdaemachine" | cut -f 1 -d ' ')
docker kill $(docker ps | grep "weaveworks/plugin" | cut -f 1 -d ' ')
docker kill $(docker ps | grep "weaveworks/weaveexec" | cut -f 1 -d ' ')
docker kill $(docker ps | grep "weaveworks/weave" | cut -f 1 -d ' ')


eval $(docker-machine env machine2)

docker kill $(docker ps | grep "rsommerard/wdaemachine" | cut -f 1 -d ' ')
docker kill $(docker ps | grep "weaveworks/plugin" | cut -f 1 -d ' ')
docker kill $(docker ps | grep "weaveworks/weaveexec" | cut -f 1 -d ' ')
docker kill $(docker ps | grep "weaveworks/weave" | cut -f 1 -d ' ')
