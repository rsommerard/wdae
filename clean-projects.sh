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
cd wdae-system/HelloWeave/
sbt clean

cd $ROOT
cd wdae-system/WDAENode/WDAEEmulator/
sbt clean

cd $ROOT
cd wdae-system/WDAENode/WDAEMachine/
sbt clean

cd $ROOT
cd wdae-system/WDAEMaster/
sbt clean

# Android part
cd $ROOT
cd wdae-android/WDAEExample/
./gradlew clean

cd $ROOT
cd wdae-android/WifiDirectExample/
./gradlew clean

# Docker part
cd $ROOT
cd wdae-system/HelloWeave/docker/basic-machine/
rm *.zip

cd $ROOT
cd wdae-system/HelloWeave/docker/docker-machine/
rm *.zip

cd $ROOT
cd wdae-system/WDAEMaster/docker/docker-machine/
rm *.zip

cd $ROOT
cd wdae-system/WDAENode/WDAEEmulator/docker/docker-machine/
rm *.zip

cd $ROOT
cd wdae-system/WDAENode/WDAEMachine/docker/docker-machine/
rm *.zip
