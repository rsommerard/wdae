#!/bin/bash

cd ../WDAEExample/
./gradlew clean assembleDebug

cd ../WDAEServer/
sbt clean universal:packageBin

cd ../WDAEDocker/
cp ../WDAEServer/target/universal/wdaeserver-1.0.zip .
cp ../WDAEExample/app/build/outputs/apk/app-debug.apk apk/

docker build -t android-api22-x86 .
