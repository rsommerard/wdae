#!/bin/bash

ME=$(ip route get 1 | awk '{print $NF;exit}')
echo "IP=$ME"

weave launch
eval $(weave env)

echo "Ready on [$ME]"

docker run --rm -e WEAVE_CIDR=10.32.0.42/12 rsommerard/hello-weave master
