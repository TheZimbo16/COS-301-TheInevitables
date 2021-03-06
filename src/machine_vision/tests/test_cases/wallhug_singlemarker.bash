#!/bin/bash
../detect_markers -v=singlemarker_wall_hug.mp4 -d=0 -c="calibration_data_phone" -l=0.193 --dot_size=0 --portrait --dp=detector_params.yml --print_ids > singlemarker_wall_hug_actual
echo "Testing single marker wall hugging movement"
bash test_expectation.bash test_cases/singlemarker_wall_hug_expected singlemarker_wall_hug_actual
rm singlemarker_wall_hug_actual
