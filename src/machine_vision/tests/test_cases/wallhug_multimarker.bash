#!/bin/bash
../detect_markers -v=multimarker_wall_hug.mp4 -d=0 -c="calibration_data_phone" -l=0.193 --dot_size=0 --portrait --dp=detector_params.yml --print_ids > multimarker_wall_hug_actual
echo "Testing multimarker wall hugging movement"
bash test_expectation.bash test_cases/multimarker_wall_hug_expected multimarker_wall_hug_actual
rm multimarker_wall_hug_actual
