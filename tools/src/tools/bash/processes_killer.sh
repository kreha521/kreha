#!/bin/bash

if [ $# -ne 1 -o "$1" = "root" -o "$1" = $(id -un) ]; then
  echo "Invalid argument! Please specify a valid username to kill its processes."
  exit 1
fi

pkill -9 -u "$1" >& /dev/null
ipcs -m | egrep "( $1 )" | awk '{print $2}' | xargs -n 1 ipcrm -m >& /dev/null
ipcs -s | egrep "( $1 )" | awk '{print $2}' | xargs -n 1 ipcrm -s >& /dev/null
ipcs -q | egrep "( $1 )" | awk '{print $2}' | xargs -n 1 ipcrm -q >& /dev/null

exit 0