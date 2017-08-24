#!/bin/bash
#Adapted from https://stackoverflow.com/a/38937732

ggID=$1  
ggURL='https://drive.google.com/uc?export=download'  
filename=$2  
getcode="$(awk '/_warning_/ {print $NF}' /tmp/gcokie)"  
curl -Lb /tmp/gcokie "${ggURL}&confirm=${getcode}&id=${ggID}" -o "${filename}"
