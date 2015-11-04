#!/bin/bash

cd res
killall codegence-aurora
#TODO demonize this
#while :
#do
../../bin/main/codegence-aurora
#done
cd -
