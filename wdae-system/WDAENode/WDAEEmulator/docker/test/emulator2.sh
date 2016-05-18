#!/bin/bash

eval $(weave env)

MACHINE_CID=$(docker ps | grep wdaemachine | awk '{print $1;exit}')
MACHINE=$(weave ps $MACHINE_CID | awk '{print $NF;exit}' | cut -d '/' -f 1)

echo $MACHINE

# docker run --rm -it --privileged rsommerard/wdaeemulatortest $MACHINE
docker run --rm -it --privileged rsommerard/wdaeemulatortest /wdaeemulator-1.0/bin/wdaeemulator $MACHINE
