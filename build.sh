#!/bin/bash

source config.properties

# remove old files
if [[ -d ./output ]]; then
  rm -rf ./output
fi
mkdir output
mkdir output/jar
mkdir output/classes
mkdir output/war

if [[ -d artifacts ]]; then
  rm -rf artifacts
fi
mkdir artifacts

# builds the classes
javac -classpath ".:./lib/servlet-api.jar" -d output/classes -s output/classes -h output $(find src -type f -name '*.java')

# add necessary files for the jar
cp -r ./src/META-INF output/jar
cp ./*.properties output/jar
cp -r ./lib output/jar

# move to output/jar
cd output/jar

# builds the jar with necessary files
jar cvfm ../../artifacts/es.uco.pw.jar META-INF/MANIFEST.MF ./config.properties ./sql.properties $(find . -type f -name '*.class')

# move to root
cd ../..

# adds the necessary files for the war
cp -r WebContent/* output/war
cp -r lib output/war/WEB-INF
cp -r output/classes output/war/WEB-INF/classes
cp ./config.properties output/war
cp ./sql.properties output/war

# move to output/war
cd output/war

# builds the war
jar cvf ../../artifacts/es.uco.pw.war .