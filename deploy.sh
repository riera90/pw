#!/bin/bash
./build.sh

rm /opt/apache-tomcat-8.5.60/webapps/es/uco/pw/* -rf
cp ./artifacts/es.uco.pw.war /opt/apache-tomcat-8.5.60/webapps/es/uco/pw
cp ./artifacts/es.uco.pw.jar /opt/apache-tomcat-8.5.60/webapps/es/uco/pw
cd /opt/apache-tomcat-8.5.60/webapps/es/uco/pw
jar xvf es.uco.pw.war

/opt/apache-tomcat-8.5.60/bin/shutdown.sh
/opt/apache-tomcat-8.5.60/bin/startup.sh

sleep 2

sudo lsof -i -n -P | grep LISTEN | grep java

firefox http://localhost:8080/es/uco/pw/WebContent