package org.opencv;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

public class Smoother {

    public static void main(String[] args) {
        smooth("/home/remipassmoilesel/projects/java-experiments/data/arbitrary-pictures-2/4-trees.jpg");
    }

    public static void smooth(String filename) {
        IplImage image = cvLoadImage(filename);
        if (image != null) {
            cvSmooth(image, image);
            cvSaveImage(filename, image);
            cvReleaseImage(image);
        }
    }
}