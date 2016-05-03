#!/bin/bash

ROOT="$(pwd)"
echo $ROOT

# Docker part
cd wdae-docker/hello-weave/
./clean_tmp_files.sh

cd $ROOT
cd wdae-docker/wdae/
./clean_tmp_files.sh

# Scala part
cd $ROOT
cd wdae-system/Hello/
sbt clean

cd $ROOT
cd wdae-system/WDAENode/WDAEEmulator/
sbt clean

cd $ROOT
cd wdae-system/WDAENode/WDAEMachine/
sbt clean

# Android part
cd $ROOT
cd wdae-android/WDAEExample/
./gradlew clean

cd $ROOT
cd wdae-android/WifiDirectExample/
./gradlew clean
