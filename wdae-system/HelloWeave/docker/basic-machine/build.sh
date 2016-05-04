#!/bin/bash

ROOT=$(pwd)

cd ../../
sbt clean universal:packageBin

cd $ROOT
cp ../../target/universal/helloweave-1.0.zip .

docker build -t rsommerard/helloweave .
