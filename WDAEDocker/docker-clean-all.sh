#!/bin/bash

# Kill all running containers
docker kill $(docker ps -q)

# Delete all stopped containers (including data-only containers)
docker rm $(docker ps -a -q)

# Delete all 'untagged/dangling' (<none>) images
docker rmi $(docker images -q -f dangling=true)

# Delete ALL images
docker rmi $(docker images -q)
