#!/bin/bash
# execute build
./build.sh

mkdir -p /opt/apache-tomcat-8.5.60/webapps/es/uco/pw

# clean old files
rm /opt/apache-tomcat-8.5.60/webapps/es/uco/pw/* -rf

# add both jar and war
cp artifacts/es.uco.pw.war /opt/apache-tomcat-8.5.60/webapps/es/uco/pw
# cp artifacts/es.uco.pw.jar /opt/apache-tomcat-8.5.60/webapps/es/uco/pw

# change directory to deploy folder
cd /opt/apache-tomcat-8.5.60/webapps/es/uco/pw

# extract the war
jar xvf es.uco.pw.war
rm es.uco.pw.war

# restart tomcat
/opt/apache-tomcat-8.5.60/bin/shutdown.sh
/opt/apache-tomcat-8.5.60/bin/startup.sh

# show tomcat information and launch a firefox tab
sleep 2
sudo lsof -i -n -P | grep LISTEN | grep java
firefox http://localhost:8080/es/uco/pw