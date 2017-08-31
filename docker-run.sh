#!/usr/bin/env bash

source ./docker-env.sh

# Starts all applications. Run 'mvn docker:build' in platform and webapp before this script to create docker images.

# Remove existing containers
docker-compose stop
docker-compose rm -f

## Start new containers

docker-compose up -d


# Show logs. Hit 'Ctrl + c' to exit.
# docker-compose logs -f

while ! echo exit | nc localhost ${SERVER_PORT}; do sleep 10; done
