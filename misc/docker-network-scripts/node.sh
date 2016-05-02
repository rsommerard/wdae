#!/bin/bash

if [ "$(id -u)" != "0" ]; then
  echo "Script need to be launched as root."
  exit 1
fi

if [ $# == 0 ] ; then
    echo "Usage: node.sh <master_ip>"
    exit 1;
fi

MASTER=$1
echo "MASTER_IP=$MASTER"

ME=$(ip route get 1 | awk '{print $NF;exit}')
echo "IP=$ME"

echo "Docker daemon initialization..."
systemctl stop docker
docker daemon -H tcp://$ME:2375 -H unix:///var/run/docker.sock --cluster-store consul://$MASTER:8500 --cluster-advertise $MASTER:2375 &

# Pull images before launching
echo "Pulling images..."
docker pull swarm

echo "Swarm node initialization..."
docker run -d swarm join --advertise=$ME:2375 consul://$MASTER:8500/
