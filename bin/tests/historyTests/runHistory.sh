#!/bin/bash

git pull
rm -Rf Summary.csv
NOW=Run_History_$(date +"%m-%d-%y"--"%T")
mkdir $NOW
./jmeter.sh -n -t runHistory.jmx -l scriptresults.jtl -JNUM_OF_USERS=10
./jmeter -g Summary.csv -o $NOW
git add -A
git commit -m "added $NOW results"
git push
