package org.opencv;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;
import static org.bytedeco.javacpp.opencv_stitching.*;

public class CustomOpencvStitcher {
    private boolean try_use_gpu = true;

    public static void main(String[] args) {

        System.out.println("Starting main");

        String root = "data/toStitch/";
        ArrayList<Path> images = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            images.add(Paths.get(root + "row_00" + i + ".tif"));
        }

        new CustomOpencvStitcher().stitch(images, Paths.get("data/toStitch/result.jpg"));
    }

    public void stitch(List<Path> pieces, Path resultPath){

        Mat resultMat = new Mat();
        Stitcher stitcher = Stitcher.createDefault(try_use_gpu);

        MatVector imgs = readImages(pieces);

        System.out.println("Starting stitching");
        int status = stitcher.stitch(imgs, resultMat);

        if (status != Stitcher.OK) {
            System.out.println("Can't stitch images, error code = " + status);
            System.exit(-1);
        }

        imwrite(resultPath.toString(), resultMat);
        System.exit(0);
    }

    private MatVector readImages(List<Path> pieces) {

        System.out.println("Reading images");

        MatVector vector = new MatVector();

        for(Path p : pieces){

            try {
                p.toRealPath();
            } catch (IOException e) {
                throw new RuntimeException("Not an image: " + p, e);
            }
            vector.resize(vector.size() + 1);
            vector.put(vector.size() - 1, imread(p.toAbsolutePath().toString()));
        }

        return vector;
    }

}