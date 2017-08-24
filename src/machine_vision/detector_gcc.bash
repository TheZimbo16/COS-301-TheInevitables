#!/bin/bash
g++-5 --std=c++14 detect_markers.cpp -Wfatal-errors $(pkg-config --libs --cflags opencv) -Ofast -o detect_markers
