#!/bin/bash

# Path to the folder where you want to remove all files
DIR="/home/ayyappankalai/Server/apache-tomcat-10.1.34/webapps/webSocketTerminal/logs"

# Check if the folder exists
if [ -d "$DIR" ]; then
  # Remove all files inside the folder
  rm -rf "$DIR"/*
  echo "All files have been removed from $DIR."
else
  echo "The folder does not exist: $DIR"
fi

