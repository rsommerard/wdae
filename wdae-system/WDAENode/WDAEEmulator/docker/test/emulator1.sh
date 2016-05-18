#!/bin/bash

eval $(weave env)

MACHINE_CID=$(docker ps | grep wdaemachine | awk '{print $1;exit}')
MACHINE=$(weave ps $MACHINE_CID | awk '{print $NF;exit}' | cut -d '/' -f 1)

docker run --rm -it --privileged rsommerard/wdaeemulator $MACHINE
