#!/bin/bash
git checkout master
git pull
rm -Rf target
rm -Rf Summary.csv
NOW=User_Registration_$(date +"%m-%d-%y"--"%T")
mkdir $NOW
touch ./runLogs/$NOW.txt
./jmeter.sh -n -t UserRegistration.jmx -l scriptresults.jtl -JNUM_OF_USERS=$NUM_OF_USERS -j ./runLogs/$NOW.txt
./jmeter -g Summary.csv -o $NOW
echo 'Jmeter logs is available here:\n'
echo 'http://34.250.19.38:8080/job/Run%20user%20registration/ws/bin/runLogs/$NOW.txt'
mkdir target
cp -r $NOW/* target/
rm -Rf $NOW/
git add -A
git commit -m "added $NOW results"
