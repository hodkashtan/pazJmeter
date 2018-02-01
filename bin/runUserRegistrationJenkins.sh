#!/bin/bash
git checkout master
git pull
rm -Rf target
rm -Rf Summary.csv
NOW=User_Registration_$(date +"%m-%d-%y"--"%T")
mkdir $NOW
echo "LOG_FILE_NAME=runLogs/$NOW.txt"
./jmeter.sh -n -t UserRegistration.jmx -l scriptresults.jtl -JNUM_OF_USERS=$NUM_OF_USERS -JLOG_FILE_NAME=runLogs/$NOW.txt
./jmeter -g Summary.csv -o $NOW
./createUsers.sh
mkdir target
cp -r $NOW/* target/
git add -A
git commit -m "added $NOW results"
