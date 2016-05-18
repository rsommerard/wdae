#!/bin/bash

ROOT=$(pwd)

cd ../../
sbt clean universal:packageBin

cd $ROOT
cp ../../target/universal/wdaeemulator-1.0.zip .

cd $ROOT
cd ../../../../../wdae-android/WDAEExample/
./gradlew clean assembleDebug
cp app/build/outputs/apk/app-debug.apk $ROOT/apk/

cd $ROOT
docker build -t rsommerard/wdaeemulatortest .
