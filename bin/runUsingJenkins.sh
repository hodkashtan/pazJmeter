#!/bin/bash
git checkout master
git pull
rm -Rf target
rm -Rf Summary.csv
NOW="${JOB_NAME}_$(date +"%m-%d-%y"--"%T")"
mkdir $NOW
touch ./runLogs/$NOW.txt
./jmeter.sh -n -t $JOB_NAME.jmx -l scriptresults.jtl -JNUM_OF_USERS=$NUM_OF_USERS -j ./runLogs/$NOW.txt
./jmeter -g Summary.csv -o $NOW
echo 'Jmeter logs is available here:\n'
link="https://34.250.19.38/job/${JOB_NAME}/ws/bin/runLogs/${NOW}.txt"
echo $link
mkdir target
cp -r $NOW/* target/
rm -Rf $NOW/
git add -A
git commit -m "added $NOW results"
