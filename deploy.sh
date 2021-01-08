#!/bin/bash
# execute build
./build.sh

source config.properties

mkdir -p $APACHE_DIR/webapps/$APP_NAME

# clean old files
rm $APACHE_DIR/webapps/$APP_NAME/* -rf

# add both jar and war
cp artifacts/es.uco.pw.war $APACHE_DIR/webapps/$APP_NAME
# cp artifacts/es.uco.pw.jar $APACHE_DIR/webapps/$APP_NAME

# change directory to deploy folder
cd $APACHE_DIR/webapps/$APP_NAME

# extract the war
jar xvf es.uco.pw.war
rm es.uco.pw.war

# restart tomcat
$APACHE_DIR/bin/shutdown.sh
$APACHE_DIR/bin/startup.sh



# kill the main process of google chrome
#kill -9 $(ps aux | grep google/chrome | head -n 1 | sed -r 's/[a-zA-Z]*\ *([0-9]{2,})\ *.*/\1/g')
#sudo lsof -i -n -P | grep LISTEN | grep java
#sleep 1
#firefox --private-window http://localhost:8080/$APP_NAME
#watch tail -n 30 $APACHE_DIR/logs/catalina.out
