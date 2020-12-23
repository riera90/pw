#!/bin/bash
# execute build
./build.sh

mkdir -p /opt/apache-tomcat-8.5.60/webapps/pw

# clean old files
rm /opt/apache-tomcat-8.5.60/webapps/pw/* -rf

# add both jar and war
cp artifacts/es.uco.pw.war /opt/apache-tomcat-8.5.60/webapps/pw
# cp artifacts/es.uco.pw.jar /opt/apache-tomcat-8.5.60/webapps/es/uco/pw

# change directory to deploy folder
cd /opt/apache-tomcat-8.5.60/webapps/pw

# extract the war
jar xvf es.uco.pw.war
rm es.uco.pw.war

# restart tomcat
/opt/apache-tomcat-8.5.60/bin/shutdown.sh
/opt/apache-tomcat-8.5.60/bin/startup.sh



# kill the main process of google chrome
kill -9 $(ps aux | grep google/chrome | head -n 1 | sed -r 's/[a-zA-Z]*\ *([0-9]{2,})\ *.*/\1/g')
#sudo lsof -i -n -P | grep LISTEN | grep java
google-chrome http://localhost:8080/pw &

