package org.opencv;

import org.bytedeco.javacpp.opencv_core.*;
import org.bytedeco.javacpp.opencv_features2d;
import org.bytedeco.javacpp.opencv_features2d.*;
import org.bytedeco.javacpp.opencv_xfeatures2d.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.bytedeco.javacpp.opencv_imgcodecs.imread;

public class ResilientStitcher {

    private final HashMap<Path, KeyPointVector> interestPoints;

    public static void main(String[] args) {

        System.out.println("Starting main");

        String root = "data/toStitch/";
        ArrayList<Path> images = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            images.add(Paths.get(root + "space" + i + ".jpeg"));
        }

        new ResilientStitcher().stitch(images);


    }

    public ResilientStitcher() {
        this.interestPoints = new HashMap();
    }

    public void stitch(ArrayList<Path> images) {

        System.out.println("Start stitching");
        for (Path p : images) {

            try {
                p.toRealPath();
            } catch (IOException e) {
                throw new RuntimeException("Not an image: " + p, e);
            }

            interestPoints.put(p, surfAnalyse(p));
        }

        getCommonPoints(interestPoints.get(images.get(0)),
                interestPoints.get(images.get(1)));

    }

    private void getCommonPoints(KeyPointVector a, KeyPointVector b) {

        List commonPoints = new ArrayList();

        DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE);
//        matcher.match(a, b);
    }

    public KeyPointVector surfAnalyse(Path p) {

        System.out.println("Analysing: " + p);

        Mat image = imread(p.toAbsolutePath().toString());
        SURF detector = SURF.create();
        KeyPointVector vector = new KeyPointVector();
        detector.detect(image, vector);

        System.out.println("Found: " + vector.size() + " points");
        System.out.println("Sample: x=" + vector.get(0).pt().x() + ", y=" + vector.get(0).pt().y());
        return vector;
    }

}
