#!/bin/bash

cd WDAEDocker/
./clean_tmp_files.sh

cd ..
cd WDAEEmulator/
sbt clean

cd ..
cd WDAEMachine/
sbt clean

cd ..
cd WDAEExample/
./gradlew clean
