#!/bin/bash
#starting jenkins build 
curl -X POST --user internal.jenkins:a331672c121e65f696c08b383aa1bc52 -k https://34.250.19.38/job/DownloadProject/build
echo "Started jenkins build"
sleep 10
#checking for build status until build is done.
isRunning=$(curl -k --user internal.jenkins:a331672c121e65f696c08b383aa1bc52 https://34.250.19.38/job/DownloadProject/lastBuild/api/json | grep -c \'building\":true\')
while [  "$isRunning" == "1" ]; do
             echo "Build is still running, waiting 5 seconds and trying again"
             sleep 5
             isRunning=$(curl -k --user internal.jenkins:a331672c121e65f696c08b383aa1bc52 https://34.250.19.38/job/DownloadProject/lastBuild/api/json | grep -c \'building\":true\')
         done
#Downloading project zip & unzipping
curl -k --user internal.jenkins:a331672c121e65f696c08b383aa1bc52 https://34.250.19.38/job/DownloadProject/ws/project.zip --output projectTemp.zip
unzip projectTemp.zip

