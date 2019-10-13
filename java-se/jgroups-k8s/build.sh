#!/usr/bin/env bash

./gradlew bootJar

docker build . -t remipassmoilesel/jgroups-k8s:0.1
