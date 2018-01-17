#!/bin/bash

git pull
rm -Rf Summary.csv
NOW=User_Login_$(date +"%m-%d-%y"--"%T")
mkdir $NOW
./jmeter.sh -n -t UserLogin.jmx -l scriptresults.jtl -JNUM_OF_USERS=$NUM_OF_USERS
./jmeter -g Summary.csv -o $NOW
git add -A
git commit -m "added $NOW results"
