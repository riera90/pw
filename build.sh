#!/bin/sh
cd src
jar  cvfm ../diego_rodriguez_riera_pw.jar ../manifest.txt $(find . -type f -name '*.java')