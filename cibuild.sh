#!/bin/bash

ssh -i deploy_rsa root@35.197.156.121 <<EOF
  pkill -f "java -jar /home/infostaff/java/infostaff-0.0.1.jar"
  nohup java -jar /home/infostaff/java/infostaff-0.0.1.jar > /dev/null &
  echo "DONE"
EOF
