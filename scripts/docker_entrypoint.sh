#!/bin/sh

cd /app

java -Djava.security.egd=file:/dev/./urandom -jar app.jar