#!/bin/bash

weave launch
eval $(weave env)

docker run --rm -it -e WEAVE_CIDR=10.32.0.42/12 rsommerard/wdaemaster
