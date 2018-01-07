#!/bin/bash

git pull
rm -Rf Summary.csv
NOW=User_Registration_$(date +"%m-%d-%y"--"%T")
mkdir $NOW
./jmeter.sh -n -t UserRegistration.jmx -l scriptresults.jtl
./jmeter -g Summary.csv -o $NOW
./users/createUsers.sh
git add -A
git commit -m "added $NOW results"
git push
