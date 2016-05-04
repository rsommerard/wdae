#!/bin/bash

docker kill $(docker ps | grep "rsommerard/helloweave" | cut -f 1 -d ' ')
docker kill $(docker ps | grep "weaveworks/plugin" | cut -f 1 -d ' ')
docker kill $(docker ps | grep "weaveworks/weaveexec" | cut -f 1 -d ' ')
docker kill $(docker ps | grep "weaveworks/weave" | cut -f 1 -d ' ')
