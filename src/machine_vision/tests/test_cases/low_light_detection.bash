#!/bin/bash
../detect_markers -v=indoors_low_light.mp4 -d=0 -c="calibration_data_phone" -l=0.193 --dot_size=0 --portrait --dp=detector_params.yml --print_ids > low_light_actual
echo "Testing low light detection accuracy"
bash test_expectation.bash test_cases/low_light_expected low_light_actual
rm low_light_actual
