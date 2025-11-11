#!/bin/bash

server_name=$1
ENUM_VALUES="all newhker mysql redis nginx"

if [ -z "${server_name}" ]; then
    echo 'Please enter the service name (corresponding to docker-compose.yml)'
    for value in $ENUM_VALUES; do
        echo "- $value"
    done
    exit 1
fi

if [ "${server_name}" == "all" ]; then
    docker compose down
    
    docker images | grep ccr.ccs.tencentyun.com | awk '{print $3}' | xargs -I {} docker rmi {} 2>/dev/null || true
    docker compose up -d
    exit 0
fi

docker-compose down ${server_name}

docker images | grep ${server_name} | awk '{print $3}' | xargs -I {} docker rmi {} 2>/dev/null || true
docker-compose pull ${server_name}
docker-compose up -d ${server_name}