#!/bin/bash

expect=$1
actual=$2

comm -23 <(sort -u $expect) <(sort -u $actual) > asymmetric_diff;
#sort -u $expect
#echo "-----"
#sort -u $actual
set_diff_count=$(wc -l asymmetric_diff|cut -f-1 -d ' ')
if [ $set_diff_count -ne 0 ]
then
    echo "Test Failed, output does not contain $(cat asymmetric_diff)"
fi;
echo "-----"
comm -23 <(sort -u $actual) <(sort -u $expect) > asymmetric_diff;
set_diff_count=$(wc -l asymmetric_diff|cut -f-1 -d ' ')
if [ $set_diff_count -ne 0 ]
then
    echo "Test Failed, output should not contain $(cat asymmetric_diff)"
fi;
