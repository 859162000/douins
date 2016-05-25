#! /bin/bash

cd ../agency-service
mvn clean package install -DskipTests
cd ../agency-api
mvn clean package antrun:run -DskipTests $dev