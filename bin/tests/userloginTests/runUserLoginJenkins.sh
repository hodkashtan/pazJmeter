#!/bin/bash
git checkout master
git pull
rm -Rf target
rm -Rf Summary.csv
NOW=User_Login_$(date +"%m-%d-%y"--"%T")
mkdir $NOW
./jmeter.sh -n -t UserLogin.jmx -l scriptresults.jtl -JNUM_OF_USERS=$NUM_OF_USERS
./jmeter -g Summary.csv -o $NOW
mkdir target
cp -r $NOW/* target/
git add -A
git commit -m "added $NOW results"
