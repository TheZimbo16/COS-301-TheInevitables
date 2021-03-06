/*
By downloading, copying, installing or using the software you agree to this
license. If you do not agree to this license, do not download, install,
copy or use the software.

                          License Agreement
               For Open Source Computer Vision Library
                       (3-clause BSD License)

Copyright (C) 2013, OpenCV Foundation, all rights reserved.
Third party copyrights are property of their respective owners.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

  * Redistributions of source code must retain the above copyright notice,
    this list of conditions and the following disclaimer.

  * Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

  * Neither the names of the copyright holders nor the names of the contributors
    may be used to endorse or promote products derived from this software
    without specific prior written permission.

This software is provided by the copyright holders and contributors "as is" and
any express or implied warranties, including, but not limited to, the implied
warranties of merchantability and fitness for a particular purpose are
disclaimed. In no event shall copyright holders or contributors be liable for
any direct, indirect, incidental, special, exemplary, or consequential damages
(including, but not limited to, procurement of substitute goods or services;
loss of use, data, or profits; or business interruption) however caused
and on any theory of liability, whether in contract, strict liability,
or tort (including negligence or otherwise) arising in any way out of
the use of this software, even if advised of the possibility of such damage.
*/


#include <opencv2/highgui.hpp>
#include <opencv2/aruco.hpp>
#include <opencv2/calib3d/calib3d.hpp>
#include <opencv2/core/base.hpp>
#include <opencv2/opencv.hpp>
#include <iostream>
#include <deque>//used for the moving average window
#include <algorithm>//std::accumulate

namespace {
const char* about = "Basic marker detection";
const char* keys  =
        "{d        |       | dictionary: DICT_4X4_50=0, DICT_4X4_100=1, DICT_4X4_250=2,"
        "DICT_4X4_1000=3, DICT_5X5_50=4, DICT_5X5_100=5, DICT_5X5_250=6, DICT_5X5_1000=7, "
        "DICT_6X6_50=8, DICT_6X6_100=9, DICT_6X6_250=10, DICT_6X6_1000=11, DICT_7X7_50=12,"
        "DICT_7X7_100=13, DICT_7X7_250=14, DICT_7X7_1000=15, DICT_ARUCO_ORIGINAL = 16}"
        "{v        |       | Input from video file, if ommited, input comes from camera }"
        "{ci       | 0     | Camera id if input doesnt come from video (-v) }"
        "{c        |       | Camera intrinsic parameters. Needed for camera pose }"
        "{l        | 0.1   | Marker side lenght (in meters). Needed for correct scale in camera pose }"
        "{dp       |       | File of marker detector parameters }"
        "{r        |       | show rejected candidates too }"
        "{portrait |       | convert video to portrait mode }"
        "{ground_plane |       | do ground-plane triangulation of markers. If not specified, default to wall-plane triangulation }"
        "{mirror   |       | mirror mode for debugging purposes}"
        "{dot_size   | 20    | Size of the green and red dot}"
        "{show_axis   |       | Show the axis of the markers}"
        "{print_distance   |       | Print the euclidean distance to stdout}"
        "{print_ids   |       | Print the detected IDs to stdout}"
        "{verbose   |       | Be verbose, print readings to stderr}";
}

static bool readCameraParameters(std::string filename, cv::Mat &camMatrix, cv::Mat &distCoeffs) {
    cv::FileStorage fs(filename, cv::FileStorage::READ);
    if(!fs.isOpened())
        return false;
    fs["camera_matrix"] >> camMatrix;
    fs["distortion_coefficients"] >> distCoeffs;
    return true;
}

static bool readDetectorParameters(std::string filename, cv::Ptr<cv::aruco::DetectorParameters> &params) {
    cv::FileStorage fs(filename, cv::FileStorage::READ);
    if(!fs.isOpened())
        return false;
    fs["adaptiveThreshWinSizeMin"] >> params->adaptiveThreshWinSizeMin;
    fs["adaptiveThreshWinSizeMax"] >> params->adaptiveThreshWinSizeMax;
    fs["adaptiveThreshWinSizeStep"] >> params->adaptiveThreshWinSizeStep;
    fs["adaptiveThreshConstant"] >> params->adaptiveThreshConstant;
    fs["minMarkerPerimeterRate"] >> params->minMarkerPerimeterRate;
    fs["maxMarkerPerimeterRate"] >> params->maxMarkerPerimeterRate;
    fs["polygonalApproxAccuracyRate"] >> params->polygonalApproxAccuracyRate;
    fs["minCornerDistanceRate"] >> params->minCornerDistanceRate;
    fs["minDistanceToBorder"] >> params->minDistanceToBorder;
    fs["minMarkerDistanceRate"] >> params->minMarkerDistanceRate;
    fs["doCornerRefinement"] >> params->doCornerRefinement;
    fs["cornerRefinementWinSize"] >> params->cornerRefinementWinSize;
    fs["cornerRefinementMaxIterations"] >> params->cornerRefinementMaxIterations;
    fs["cornerRefinementMinAccuracy"] >> params->cornerRefinementMinAccuracy;
    fs["markerBorderBits"] >> params->markerBorderBits;
    fs["perspectiveRemovePixelPerCell"] >> params->perspectiveRemovePixelPerCell;
    fs["perspectiveRemoveIgnoredMarginPerCell"] >> params->perspectiveRemoveIgnoredMarginPerCell;
    fs["maxErroneousBitsInBorderRate"] >> params->maxErroneousBitsInBorderRate;
    fs["minOtsuStdDev"] >> params->minOtsuStdDev;
    fs["errorCorrectionRate"] >> params->errorCorrectionRate;
    return true;
}

void custom_drawAxis(cv::InputOutputArray _image, cv::InputArray _cameraMatrix, cv::InputArray _distCoeffs,
              cv::InputArray _rvec, cv::InputArray _tvec, float length) {

    CV_Assert(_image.getMat().total() != 0 &&
              (_image.getMat().channels() == 1 || _image.getMat().channels() == 3));
    CV_Assert(length > 0);

    // project axis points
    std::vector< cv::Point3f > axisPoints;
    axisPoints.push_back(cv::Point3f(0, 0, 0));
    axisPoints.push_back(cv::Point3f(length, 0, 0));
    axisPoints.push_back(cv::Point3f(0, length, 0));
    axisPoints.push_back(cv::Point3f(0, 0, length));

//    cv::Matx31f rot;//normalise the Rodrigues rotation vector
//    cv::normalize(_rvec.getMat(), rot);
//    axisPoints.push_back(cv::Point3f(rot(0,0)*length, rot(1,0)*length, rot(2,0)*length));

    std::vector< cv::Point2f > imagePoints;
    cv::projectPoints(axisPoints, _rvec, _tvec, _cameraMatrix, _distCoeffs, imagePoints);

    // draw axis lines
    cv::line(_image, imagePoints[0], imagePoints[1], cv::Scalar(0, 0, 255), 3);
    cv::line(_image, imagePoints[0], imagePoints[2], cv::Scalar(0, 255, 0), 3);
    cv::line(_image, imagePoints[0], imagePoints[3], cv::Scalar(255, 0, 0), 3);
    //Rodrigues vector direction
//    cv::line(_image, imagePoints[0], imagePoints[4], cv::Scalar(255, 255, 0), 3);
}

void custom_drawDetectedMarkers(cv::InputOutputArray _image, cv::InputArrayOfArrays _corners,
                         cv::InputArray _ids, cv::Scalar borderColor) {


    CV_Assert(_image.getMat().total() != 0 &&
              (_image.getMat().channels() == 1 || _image.getMat().channels() == 3));
    CV_Assert((_corners.total() == _ids.total()) || _ids.total() == 0);

    // calculate colors
    cv::Scalar textColor, cornerColor;
    textColor = cornerColor = borderColor;
    std::swap(textColor.val[0], textColor.val[1]);     // text color just sawp G and R
    std::swap(cornerColor.val[1], cornerColor.val[2]); // corner color just sawp G and B

    int nMarkers = (int)_corners.total();
    for(int i = 0; i < nMarkers; i++) {
        cv::Mat currentMarker = _corners.getMat(i);
        CV_Assert(currentMarker.total() == 4 && currentMarker.type() == CV_32FC2);

        // draw marker sides
        for(int j = 0; j < 4; j++) {
            cv::Point2f p0, p1;
            p0 = currentMarker.ptr< cv::Point2f >(0)[j];
            p1 = currentMarker.ptr< cv::Point2f >(0)[(j + 1) % 4];
            cv::line(_image, p0, p1, borderColor, 4);
        }
        // draw first corner mark
        cv::rectangle(_image, currentMarker.ptr< cv::Point2f >(0)[0] - cv::Point2f(3, 3),
                  currentMarker.ptr< cv::Point2f >(0)[0] + cv::Point2f(3, 3), cornerColor, 1, cv::LINE_AA);

    }
}


int main(int argc, char *argv[]) {
    cv::CommandLineParser parser(argc, argv, keys);
    parser.about(about);

    if(argc < 2) {
        parser.printMessage();
        return 0;
    }

    int dictionaryId = parser.get<int>("d");
    bool showRejected = parser.has("r");
    bool estimatePose = parser.has("c");
    float markerLength = parser.get<float>("l");
    bool portrait_mode = parser.has("portrait");
    bool ground_plane = parser.has("ground_plane");
    bool mirror = parser.has("mirror");
    bool show_axis = parser.has("show_axis");
    bool verbose = parser.has("verbose");
    bool print_distance = parser.has("print_distance");
    bool print_ids = parser.has("print_ids");
    int dot_size = parser.get<int>("dot_size");

    cv::Ptr<cv::aruco::DetectorParameters> detectorParams = cv::aruco::DetectorParameters::create();
    if(parser.has("dp")) {
        bool readOk = readDetectorParameters(parser.get<std::string>("dp"), detectorParams);
        if(!readOk) {
            std::cerr << "Invalid detector parameters file" << std::endl;
            return 0;
        }
    }
    detectorParams->doCornerRefinement = true; // do corner refinement in markers

    int camId = parser.get<int>("ci");

    cv::String video;
    if(parser.has("v")) {
        video = parser.get<cv::String>("v");
    }

    if(!parser.check()) {
        parser.printErrors();
        return 0;
    }

    cv::Ptr<cv::aruco::Dictionary> dictionary =
        cv::aruco::getPredefinedDictionary(cv::aruco::PREDEFINED_DICTIONARY_NAME(dictionaryId));

    cv::Mat camMatrix, distCoeffs;
    if(estimatePose) {
        bool readOk = readCameraParameters(parser.get<std::string>("c"), camMatrix, distCoeffs);
        if(!readOk) {
            std::cerr << "Invalid camera file" << std::endl;
            return 0;
        }
    }

    cv::VideoCapture inputVideo;
    int waitTime;

    if(!video.empty()) {
        inputVideo.open(video);
	//process video in realtime even if it can be done faster
//	int fps = inputVideo.get(CV_CAP_PROP_FPS);
//        waitTime = 1000/fps;
        waitTime = 1;
    } else {
        inputVideo.open(camId);
        waitTime = 10;

//        inputVideo.set(CV_CAP_PROP_FRAME_WIDTH,640);
//        inputVideo.set(CV_CAP_PROP_FRAME_HEIGHT,480);
    }

    std::deque<float> avg;
    std::deque<float> avgx;
    std::deque<float> avgy;
    std::deque<float> avgz;
		cv::VideoWriter output_cap("output.avi", 
                cv::VideoWriter::fourcc('X', 'V', 'I', 'D'),
//0x00000021,
               inputVideo.get(CV_CAP_PROP_FPS),
               cv::Size(inputVideo.get(CV_CAP_PROP_FRAME_WIDTH),
               inputVideo.get(CV_CAP_PROP_FRAME_HEIGHT)));
		int fr = 0;
    while(inputVideo.grab()&&fr < 1000) {
//++fr;
        cv::Mat image, imageCopy;
        inputVideo.retrieve(image);
        if(portrait_mode)
        {
            cv::transpose(image, image);
            cv::flip(image, image, 1);
        }
//				cv::resize(image,image,cv::Size(),0.2,0.2);
            
        std::vector< int > ids;
        std::vector< std::vector< cv::Point2f > > corners, rejected;
        std::vector< cv::Vec3d > rvecs, tvecs;

        // detect markers and estimate pose
        cv::aruco::detectMarkers(image, dictionary, corners, ids, detectorParams, rejected);
        if(estimatePose && ids.size() > 0)
            cv::aruco::estimatePoseSingleMarkers(corners, markerLength, camMatrix, distCoeffs, rvecs,
                                             tvecs);

        // draw results
        image.copyTo(imageCopy);
        if(ids.size() > 0) {
            custom_drawDetectedMarkers(imageCopy, corners, ids, cv::Scalar(0,255,0));

            if(estimatePose) {
                for(unsigned int i = 0; i < ids.size(); i++)
                {
                    if(show_axis)
                        custom_drawAxis(imageCopy, camMatrix, distCoeffs, rvecs[i], tvecs[i],
                                    markerLength * 0.5f);

//                    auto euclid_distance = cv::norm(tvecs[i]);
//                    auto euclid_distance = cv::sqrt(tvecs[i][0]*tvecs[i][0]+tvecs[i][1]*tvecs[i][1]+tvecs[i][2]*tvecs[i][2]);
                    float euclid_distance = cv::sqrt(tvecs[i][0]*tvecs[i][0]+tvecs[i][2]*tvecs[i][2]);
                        
                    if(verbose)
                    {
                        std::cerr << "ID: " << ids[i] << std::endl;
                        std::cerr << "X coordinate: " << tvecs[i][0] << std::endl;
                        std::cerr << "Y coordinate: " << tvecs[i][1] << std::endl;
                        std::cerr << "Z coordinate: " << tvecs[i][2] << std::endl;
                        std::cerr << "Euclidean distance: " << euclid_distance << std::endl;
                    }
                    if(print_distance)
                        std::cout << euclid_distance << std::endl;
                    if(print_ids)
                        std::cout << ids[i] << std::endl;

                    int width = inputVideo.get(CV_CAP_PROP_FRAME_WIDTH);
                    int height = inputVideo.get(CV_CAP_PROP_FRAME_HEIGHT);
cv::Point2f middle = (corners[i][0]+corners[i][1]+corners[i][2]+corners[i][3])/4;
cv::line(imageCopy,cv::Point(0,middle.y),cv::Point(width+middle.x,middle.y), cv::Scalar(0,255,0),4);
cv::line(imageCopy,cv::Point(middle.x,0),cv::Point(middle.x,height+middle.y), cv::Scalar(0,255,0),4);
//cv::line(imageCopy);


                    cv::circle(imageCopy,CvPoint((width/2),(height/2)),dot_size,CV_RGB(0,255,0), -1, CV_AA);

                    cv::Mat rotation_matrix;
                    cv::Rodrigues(rvecs[i],rotation_matrix);
                    cv::Matx33f rotation_matrix_float = rotation_matrix;
                    cv::Matx31f ctv = -rotation_matrix_float.t()*tvecs[i];

                    int window_size = 10;
                    avgx.push_front(ctv(0,0));
                    if(avgx.size()>window_size)
                        avgx.pop_back();
                    if(ground_plane)
                    {
                        avgy.push_front(ctv(1,0));
                        if(avgy.size()>window_size)
                            avgy.pop_back();
                    }
                    else
                    {
                        avgz.push_front(ctv(2,0));
                        if(avgz.size()>window_size)
                            avgz.pop_back();
                    }
                    
                    float xavg = std::accumulate(avgx.begin(),avgx.end(),0.0)/avgx.size();
                    float yavg;
                    float zavg;
                    if(ground_plane)
                        yavg = std::accumulate(avgy.begin(),avgy.end(),0.0)/avgy.size();
                    else
                        zavg = std::accumulate(avgz.begin(),avgz.end(),0.0)/avgz.size();
                    
                    if(ground_plane)
                        cv::circle(imageCopy,CvPoint((width/2)+xavg*100,(height/2)+yavg*100),dot_size,CV_RGB(255,0,0), -1, CV_AA);
                    else
                        cv::circle(imageCopy,CvPoint((width/2)+xavg*100,(height/2)+zavg*100),dot_size,CV_RGB(255,0,0), -1, CV_AA);
                    if(verbose)
                    {
                        std::cerr << "Viewer X coordinate: " << xavg << std::endl;
                        std::cerr << "Viewer Y coordinate: " << yavg << std::endl;
                    }
                    if(ground_plane)
                        avg.push_front(cv::sqrt(ctv(0,0)*ctv(0,0)+ctv(1,0)*ctv(1,0)));
                    else
                        avg.push_front(cv::sqrt(ctv(0,0)*ctv(0,0)+ctv(2,0)*ctv(2,0)));

                    if(avg.size()>window_size)
                        avg.pop_back();
                    
                    if(verbose)
                    {    
                        if(ground_plane)
                            std::cerr << "Ground plane distance: ";
                        else
                            std::cerr << "Wall plane distance: ";
                        std::cerr << std::accumulate(avg.begin(),avg.end(),0.0)/avg.size() << std::endl;
//                        std::cerr << "Rotation: " << cv::norm(rvecs[i]) << std::endl;
                        std::cerr << std::endl;
                    }
                }
            }
        }

        if(showRejected && rejected.size() > 0)
            cv::aruco::drawDetectedMarkers(imageCopy, rejected, cv::noArray(), cv::Scalar(100, 0, 255));
        if(mirror)
            cv::flip(imageCopy, imageCopy, 1);
				output_cap << imageCopy;
        cv::imshow("out", imageCopy);
        char key = (char)cv::waitKey(waitTime);
        if(key == 27) break;
    }
		output_cap.release();

    return 0;
}
