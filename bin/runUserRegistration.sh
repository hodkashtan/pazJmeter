#!/bin/bash

cd /home/centos/pazJmeter/bin
git pull
NOW=$(date +"%m-%d-%y"--"%T")
mkdir $NOW
./jmeter.sh -n -t UserRegistration.jmx -l scriptresults.jtl
./jmeter -g Summary.csv -o $NOW
git add -A
git commit -m "added $NOW results"
git push
