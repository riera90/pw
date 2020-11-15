#!/bin/bash

if [[ -d ./output ]]; then
  rm -rf ./output
fi
mkdir output

if [[ -f ./es.uco.pw.jar ]]; then
  rm es.uco.pw.jar
fi

javac -d ./output -s ./output -h ./output $(find ./src -type f -name '*.java')

cp -r ./src/META-INF output
cp .properties output
cp -r ./lib output

cd output

jar cvfm ../es.uco.pw.jar ./META-INF/MANIFEST.MF .properties ./lib/mysql-connector.jar $(find . -type f -name '*.class')