#!/bin/bash

# Docker part
cd wdae-docker/hello-weave/
./clean_tmp_files.sh

cd ../wdae/
./clean_tmp_files.sh

# Scala part
cd ../../
cd wdae-system/WDAEEmulator/
sbt clean

cd ../
cd WDAEMachine/
sbt clean

cd ../
cd Hello/
sbt clean

# Android part
cd ../../
cd wdae-android/WDAEExample/
./gradlew clean

cd ../
cd WifiDirectExample/
./gradlew clean
