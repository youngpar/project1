#!/usr/bin/bash

gradle bootJar && java -jar --spring.profiles.active=dev
