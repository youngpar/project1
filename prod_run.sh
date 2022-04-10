#!/usr/bin/zsh

gradle bootJar && java -jar --spring.profiles.active=prod
