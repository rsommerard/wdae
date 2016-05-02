# wdae

Wifi Direct Android Emulator

## Deployment

1. Get master node ip
`ip route get 1 | awk '{print $NF;exit}'`

## Test

All next commands are relative to the project root folder.

First, launch the machine server:

```bash
cd WDAEMachine/
./run.sh
```

Then, build the docker image:

```bash
cd WDAEDocker/
./dockerize.sh
```

Finally, launch emulators by repeating these command:

```bash
cd WDAEDocker/
./run.sh
```
