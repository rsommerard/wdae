#!/bin/bash

if [ "$(id -u)" != "0" ]; then
  echo "Script need to be launched as root."
  exit 1
fi

ME=$(ip route get 1 | awk '{print $NF;exit}')
echo "IP=$ME"

# Init docker daemon
echo "Docker daemon initialization..."
systemctl stop docker
docker daemon -H tcp://$ME:2375 -H unix:///var/run/docker.sock --cluster-store consul://$ME:8500 --cluster-advertise $ME:2375 &

# Pull images before launching
echo "Pulling images..."
docker pull progrium/consul
docker pull swarm

# Init consul
echo "Consul initialization..."
docker run -d -p 8500:8500 --name=consul progrium/consul -server -bootstrap

sleep 5

# Init docker swarm manager
echo "Swarm manager initialization..."
docker run -d -p 4000:2375 swarm manage consul://$ME:8500/

sleep 5

echo "Swarm node initialization..."
docker run -d swarm join --advertise=$ME:2375 consul://$ME:8500/

sleep 5

# create the overlay network
echo "Docker network overlay initialization..."
docker -H tcp://$ME:4000 network create -d overlay wdae
