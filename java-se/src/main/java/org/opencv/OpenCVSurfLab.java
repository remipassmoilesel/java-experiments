// Commented due to a version change

//package org.opencv;
//
//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.core.MatOfKeyPoint;
//import org.opencv.features2d.FeatureDetector;
//import org.opencv.features2d.KeyPoint;
//import org.opencv.highgui.Highgui;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
///**
// * Created by remipassmoilesel on 14/11/16.
// */
//public class OpenCVSurfLab {
//
//    public static void main(String[] args) throws InterruptedException {
//
//
//        // Load libraries. Native executables should be in theses locations
//        System.out.println(System.getProperty("java.library.path"));
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//        Thread.sleep(2000);
//
//        long before = System.currentTimeMillis();
//
//        Path p = Paths.get("huge_image.png");
//
//        MatOfKeyPoint objectKeyPoints = new MatOfKeyPoint();
//        FeatureDetector det = FeatureDetector.create(FeatureDetector.SURF);
//        Mat objectImage = Highgui.imread(p.toString(), Highgui.CV_LOAD_IMAGE_COLOR);
//        det.detect(objectImage, objectKeyPoints);
//
//        long time = System.currentTimeMillis() - before;
//
//        System.out.println("Duration: " + time / 1000);
//
//        KeyPoint[] keypoints = objectKeyPoints.toArray();
//
//        System.out.println(keypoints);
//    }
//}
