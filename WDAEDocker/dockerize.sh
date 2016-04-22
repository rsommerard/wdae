#!/bin/bash

cd ../WDAEExample/
./gradlew clean assembleDebug

cd ../WDAEEmulator/
sbt clean universal:packageBin

cd ../WDAEDocker/
cp ../WDAEEmulator/target/universal/wdaeemulator-1.0.zip .
cp ../WDAEExample/app/build/outputs/apk/app-debug.apk apk/

docker build -t android-api22-x86 .
