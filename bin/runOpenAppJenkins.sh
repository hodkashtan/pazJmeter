#!/bin/bash

git pull
git checkout master
rm -Rf Summary.csv
NOW=Open_app_$(date +"%m-%d-%y"--"%T")
mkdir $NOW
./jmeter.sh -n -t openApp.jmx -l scriptresults.jtl -JNUM_OF_USERS=$NUM_OF_USERS
./jmeter -g Summary.csv -o $NOW
git add -A
git commit -m "added $NOW results"
git push
