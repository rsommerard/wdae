#!/bin/bash

if [ $# == 0 ] ; then
    echo "Usage: node.sh <master_ip>"
    exit 1;
fi

MASTER=$1
echo "MASTER_IP=$MASTER"

ME=$(ip route get 1 | awk '{print $NF;exit}')
echo "IP=$ME"

weave launch $MASTER
eval $(weave env)

echo "Ready on [$ME]"

docker run -it --ip 172.18.0.42 alpine /bin/sh
