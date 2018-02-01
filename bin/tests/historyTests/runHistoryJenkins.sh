#!/bin/bash
WORKSPACE='/home/centos/pazJmeter'
git checkout master
git pull
rm -Rf target
rm -Rf HistorySummary.csv
NOW=Run_History_$(date +"%m-%d-%y"--"%T")
mkdir $NOW
chmod +x $WORKSPACE/bin/jmeter.sh 
-n -t runHistory.jmx -l chmod +x $WORKSPACE/bin/scriptresults.jtl -JNUM_OF_USERS=$NUM_OF_USERS
chmod +x $WORKSPACE/bin/jmeter 
-g HistorySummary.csv -o $NOW
chmod +x $WORKSPACE/bin/createUsers.sh
mkdir target
cp -r $NOW/* target/
git add -A
git commit -m "added $NOW results"
