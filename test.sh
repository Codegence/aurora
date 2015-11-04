#!/bin/bash

# test project
rmdir -rf bin/test
mkdir -p bin/test
cd bin/test
cmake -DCMAKE_BUILD_TYPE=Debug ../../prj/test
#TODO fix this condition
if [ ! make -j8 ]; then
    bin/test/codegence-aurora-test
fi
cd -
