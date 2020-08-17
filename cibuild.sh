#!/bin/bash
sshpass -p "lilama1996" ssh -t  -o StrictHostKeyChecking=no root@104.199.242.108 <<EOF
  echo "START INSTALL"
  pkill -f "java -jar /home/infostaff/java/infostaff-0.0.1.jar"
  pkill -f "java -jar infostaff-0.0.1.jar"
#  sleep 1
  nohup java -jar /home/infostaff/java/infostaff-0.0.1.jar > /dev/null 2>&1 &
  echo "INSTALL DONE"
EOF
