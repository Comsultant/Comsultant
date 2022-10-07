#!/bin/bash

/home/hadoop/hadoop/bin/hadoop jar /home/hadoop/Project/ssafy.jar comsultant comsultant comsultant_out
/home/hadoop/hadoop/bin/hdfs dfs -getmerge comsultant_out/part* /home/hadoop/Project/comsultant_out.txt
java -jar /home/hadoop/Project/mongotest.jar
