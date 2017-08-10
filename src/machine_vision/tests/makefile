dependencies: build
	bash gdrive_download.bash 0B6HViLClzcELYmkyd2drdEFiQ28 7_meter_stationary_low_light.webm;
	bash gdrive_download.bash 0B6HViLClzcELZ3o4X0ZrUXhhcDQ 3_meter_stationary_low_light.webm;
	bash gdrive_download.bash 0B6HViLClzcELbkpzTkl6Vm9CS2c long_range_outdoors.webm;
	bash gdrive_download.bash 0B6HViLClzcELdzBvemtHVGx5cFU indoors_low_light.mp4;
	bash gdrive_download.bash 0B6HViLClzcELekNlbkVFR0tPTzA singlemarker_wall_hug.mp4
	bash gdrive_download.bash 0B6HViLClzcELWXN2MkJzYWZYcTg multimarker_wall_hug.mp4;

build:
	cd .. && bash detector_gcc.bash

tests: build
	bash low_light_detection.bash
	bash wallhug_multimarker.bash
	bash wallhug_singlemarker.bash
