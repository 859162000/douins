#! /bin/bash

cd ../service
mvn clean package install -DskipTests
cd ../api
mvn clean package antrun:run -DskipTests $*