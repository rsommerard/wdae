#!/bin/bash

echo "y" | docker-machine rm master
echo "y" | docker-machine rm node1
echo "y" | docker-machine rm node2
