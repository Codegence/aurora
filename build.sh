#!/bin/bash

# build project
rmdir -rf bin/main
mkdir -p bin/main
cd bin/main
cmake -DCMAKE_BUILD_TYPE=Debug ../../prj/main
make -j8
cd -
