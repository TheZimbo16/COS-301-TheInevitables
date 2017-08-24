#include <jni.h>
#include <string>
#include <opencv2/highgui.hpp>
#include <opencv2/aruco.hpp>
#include <opencv2/calib3d/calib3d.hpp>
#include <opencv2/core/base.hpp>
#include <opencv2/opencv.hpp>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_dell_nativetest2_MainActivity_parseImg(JNIEnv *env, jobject, jlong addrRgba) {
    //cv::Mat& in= *(cv::Mat*)addrRgba;
    cv::Mat& image= *(cv::Mat*)addrRgba;
    cv::Size image_size = image.size();
//    cv::resize(image,image,cv::Size(),0.5,0.5);
    //cv::Mat image;
//    cv::cvtColor(image,image,cv::COLOR_RGBA2RGB);

    int dictionaryId = 0;
    bool showRejected = false;
    bool estimatePose = true;
    float markerLength = 0.19;
    bool portrait_mode = false;
    bool show_axis = true;

    cv::Ptr<cv::aruco::DetectorParameters> detectorParams = cv::aruco::DetectorParameters::create();
    //detectorParams->doCornerRefinement = true; // do corner refinement in markers
    detectorParams->errorCorrectionRate = 0.0;
    detectorParams->minMarkerPerimeterRate= 0.01;
    detectorParams->adaptiveThreshWinSizeMin= 3;
    detectorParams->adaptiveThreshWinSizeMax= 23;
    detectorParams->adaptiveThreshWinSizeStep= 20;
    detectorParams->minCornerDistanceRate= 0.0005;
    detectorParams->minMarkerDistanceRate= 0.0005;
    detectorParams->perspectiveRemovePixelPerCell= 20;
    cv::Ptr<cv::aruco::Dictionary> dictionary =
            cv::aruco::getPredefinedDictionary(cv::aruco::PREDEFINED_DICTIONARY_NAME(dictionaryId));

    //TODO: reimplement camera calibration but for now use an arbitrary camera's calibration data
//    cv::Mat camMatrix = cv::Mat::eye(3, 3, CV_64F);//identity
//		cv::Mat distCoeffs = cv::Mat::zeros(8, 1, CV_64F);

    double camArray[] = {5.2233044931406516e+02, 0.0, 3.2247123869965316e+02, 0.0,
                         5.2369253342585421e+02, 2.3053335623802445e+02, 0.0, 0.0, 1.0};
    cv::Mat camMatrix = cv::Mat(3,3,CV_64F,camArray);
    double distCoeffsArray[] = {1.1652294193713529e-01, -2.4414826689297731e-01,
                                -1.4805412228480880e-03, 8.0391637416863543e-04,
                                -5.7718269356871667e-02};
    cv::Mat distCoeffs = cv::Mat(1,5,CV_64F,distCoeffsArray);

    //cv::Mat imageCopy;
    if(portrait_mode)
    {
        cv::transpose(image, image);
        cv::flip(image, image, 1);
    }

    std::vector< int > ids;
    std::vector< std::vector< cv::Point2f > > corners, rejected;
    std::vector< cv::Vec3d > rvecs, tvecs;

    // detect markers and estimate pose
    cv::aruco::detectMarkers(image, dictionary, corners, ids, detectorParams, rejected);
    if(estimatePose && ids.size() > 0)
        cv::aruco::estimatePoseSingleMarkers(corners, markerLength, camMatrix, distCoeffs, rvecs,
                                             tvecs);

    // draw results
    //image.copyTo(imageCopy);
    if(ids.size() > 0) {
        cv::aruco::drawDetectedMarkers(image, corners, ids);

        if(estimatePose) {
            for(unsigned int i = 0; i < ids.size(); i++)
            {
                if(show_axis)
                    cv::aruco::drawAxis(image, camMatrix, distCoeffs, rvecs[i], tvecs[i],
                                    markerLength * 2.5f);
            }
        }
    }

    if(showRejected && rejected.size() > 0)
        cv::aruco::drawDetectedMarkers(image, rejected, cv::noArray(), cv::Scalar(100, 0, 255));
//    image = imageCopy;
//    cv::cvtColor(imageCopy,imageCopy,cv::COLOR_RGB2RGBA);
//    cv::resize(image,image,image_size);
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
