# flags
if(CMAKE_COMPILER_IS_GNUCXX)
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -std=gnu++14 -pedantic -pthread")
    if(CMAKE_BUILD_TYPE MATCHES "Debug")
        set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -O0 -g3 -gdwarf-2")
    else()
        set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -O3")
    endif()
endif()

# includes
set(SRC_DIR "${CMAKE_CURRENT_SOURCE_DIR}/../../src")
include_directories("${SRC_DIR}/main")

# install helper
macro(install_tree FILE_LIST)
    foreach(LEAF ${${FILE_LIST}})
        get_filename_component(DIR ${LEAF} PATH)
        string(REPLACE "${SRC_DIR}/main" "" DIR ${DIR})
        install(FILES ${LEAF} DESTINATION "include/${DIR}")
    endforeach(LEAF)
endmacro(install_tree)

# common libraries
set(LIB_COMMON
    PocoFoundation PocoUtil PocoNet jsoncpp
)
