package org.opencv;

import org.bytedeco.javacpp.opencv_xfeatures2d.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_stitching.*;

public class SurfAnalyse {

    public static void main(String[] args) throws Exception {
        Path imageDirectory = Paths.get("data/arbitrary-pictures-2");
        if (Files.isDirectory(imageDirectory) == false) {
            throw new Exception("Image directory does not exist");
        }

        long l = 0;
        for (Path i : Files.newDirectoryStream(imageDirectory)) {
            System.out.println(String.format("Processing: id=%d, path=%s", l, i));
            surfAnalyse(i);
            l++;
        }
    }

    public static void surfAnalyse(Path p) {
        Mat image = imread(p.toAbsolutePath().toString());
        SURF detector = SURF.create();
        KeyPointVector vector = new KeyPointVector();
        detector.detect(image, vector);

        System.out.println("vector");
        System.out.println(vector);
        System.out.println("vector.size()");
        System.out.println(vector.size());
        System.out.println("vector.get(0)");
        System.out.println(vector.get(0));
    }

}
