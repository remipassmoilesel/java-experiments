#!/usr/bin/env bash

set -e

echo
echo "Building Java application"
echo

./gradlew bootJar

echo
echo "Building Docker image"
echo

eval $(minikube docker-env)

docker build . -t remipassmoilesel/jgroups-k8s:0.1
