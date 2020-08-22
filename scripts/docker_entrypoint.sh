#!/bin/sh

cd /app

#java -Djava.security.egd=file:/dev/./urandom -javaagent:/app/newrelic.jar -jar app.jar
java -Djava.security.egd=file:/dev/./urandom -jar app.jar