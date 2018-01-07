#!/bin/bash
rm users.csv
for filename in ./users/*; do
        echo -n "$filename" | cut -c 6- >> "users1.csv"
        #echo -n ",">> "users.csv"
        #AccessToken=$(grep -Eo '.accessToken.{0,143}' "$filename" | cut -c 16-)
        #echo -n "$AccessToken" >> "users.csv"
        echo "" >> "users1.csv"
done
sed '/^$/d' users1.csv > users.csv
rm users1.csv
