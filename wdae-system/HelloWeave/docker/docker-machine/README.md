# Docker-machine deployment

## Initialization

- `./clean.sh` to be sure that no docker container are still running.
- `./delete.sh` to be sure that no docker-machine are still running.
- `sudo ./install.sh` to install weave.
- `./build.sh` to build the docker image.

## Launch containers

- `./master.sh` start master node on docker-machine.
- `./node1.sh` start the first node on docker-machine.
- `./node2.sh` start the second node on docker-machine.

## Stop execution

- `exit`
