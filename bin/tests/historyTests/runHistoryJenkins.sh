#!/bin/bash
WORKSPACE='/home/centos/pazJmeter'
git checkout master
git pull
rm -Rf target
rm -Rf HistorySummary.csv
NOW=Run_History_$(date +"%m-%d-%y"--"%T")
mkdir $NOW
$WORKSPACE/bin/jmeter.sh -n -t runHistory.jmx -l $WORKSPACE/bin/scriptresults.jtl -JNUM_OF_USERS=$NUM_OF_USERS
$WORKSPACE/bin/jmeter -g HistorySummary.csv -o $NOW
$WORKSPACE/bin/createUsers.sh
mkdir target
cp -r $NOW/* target/
git add -A
git commit -m "added $NOW results"
