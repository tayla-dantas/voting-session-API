#!/bin/zsh
 # Run chmod 777 build-api.sh before running this script
vagrant up
docker kill $(docker ps -a -q)
docker rm $(docker ps -a -q)
source ~/.zshrc
docker system prune -a
./mvnw  clean install
./mvnw  compile
./mvnw  package
docker build .
docker-compose up