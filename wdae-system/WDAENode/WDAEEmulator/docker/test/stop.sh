#!/bin/bash

docker kill $(docker ps | grep "rsommerard/wdaemaster" | awk '{ print $1; }')
docker kill $(docker ps | grep "rsommerard/wdaemachine" | awk '{ print $1; }')
docker kill $(docker ps | grep "rsommerard/wdaeemulator" | awk '{ print $1; }')
docker kill $(docker ps | grep "weaveworks/plugin" | cut -f 1 -d ' ')
docker kill $(docker ps | grep "weaveworks/weaveexec" | cut -f 1 -d ' ')
docker kill $(docker ps | grep "weaveworks/weave" | cut -f 1 -d ' ')
