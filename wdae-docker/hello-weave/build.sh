#!/bin/bash

cd ../../wdae-system/Hello/
sbt clean universal:packageBin

cd ../../wdae-docker/hello-weave/
cp ../../wdae-system/Hello/target/universal/hello-1.0.zip tmp/

docker build -t rsommerard/hello-weave .
