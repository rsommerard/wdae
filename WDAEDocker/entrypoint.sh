#!/bin/bash

echo "Starting wdaeserver..."

./wdaeserver-1.0/bin/wdaeserver &

echo "Starting emulator[5554]..."

emulator64-x86 -avd AndroidAPI22x86 -no-skin -no-audio -no-window -no-boot-anim -noskin -gpu off -port 5554 &

echo "Waiting for emulator to start..."

bootcompleted=""
failcounter=0
until [[ "$bootcompleted" =~ "1" ]]; do
   bootcompleted=`adb -e shell getprop sys.boot_completed 2>&1`
   if [[ "$bootcompleted" =~ "not found" ]]; then
      let "failcounter += 1"
      if [[ $failcounter -gt 60 ]]; then
        echo "  Failed to start emulator"
        exit 1
      fi
   fi
   sleep 1
done

echo "Emulator started."

echo "Installing the apk..."

adb install -r /app-debug.apk
adb logcat -c

adb shell am start -n "fr.inria.rsommerard.wdaeexample/.MainActivity"

adb logcat
