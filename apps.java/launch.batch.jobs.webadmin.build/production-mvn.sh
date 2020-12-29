#!/bin/sh
mvn -s 185.240-settings.xml -DskipTests=true clean
mvn -s 185.240-settings.xml -DskipTests=true install