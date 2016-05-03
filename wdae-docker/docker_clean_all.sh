#!/bin/bash

# Kill all running containers
docker kill $(docker ps -q)

# Delete all stopped containers (including data-only containers)
docker rm -f $(docker ps -a -q)

# Delete all 'untagged/dangling' (<none>) images
docker rmi -f $(docker images -q -f dangling=true)

# Delete ALL images
docker rmi -f $(docker images -q)

# Delete ALL volumes
docker volume rm $(docker volume ls -qf dangling=true)

