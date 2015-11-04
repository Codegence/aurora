#!/bin/bash

# versions
POCO_VERSION="1.6.1"

# install system dependencies
dnf install wget gcc-c++ boost-devel jsoncpp-devel openssl-devel cmake gtest-devel -y

# enter deps directory
mkdir -p deps
cd deps

# install Poco
if [ ! -d "poco-${POCO_VERSION}-all" ]; then
  wget http://pocoproject.org/releases/poco-${POCO_VERSION}/poco-${POCO_VERSION}-all.tar.gz
  tar -xf poco-${POCO_VERSION}-all.tar.gz
  cd poco-${POCO_VERSION}-all/
  ./configure --omit=Data/ODBC,Data/MySQL --no-tests --no-samples
  make -j8
  make install
  cd -
fi

# install to system directory
cd ..
cp /usr/local/lib/* /usr/lib64/ -rn
