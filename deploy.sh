#!/bin/bash
SERVCE_NAME=mediationservice
echo "stopping docker container $SERVICE_NAME"
mkdir log
docker stop mediationservice_instance
docker rm mediationservice_instance
docker run -d -it -p 8080:8080  --name=mediationservice_instance -v `pwd`/log:/usr/share/log mediationservice

echo "$SERVICE_NAME app is Successfully deployed, listening to port 8080 , hit /index.html for swagger"