package org.imaging;

import org.apache.commons.imaging.*;
import org.apache.commons.imaging.formats.tiff.constants.TiffConstants;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by remipassmoilesel on 03/02/17.
 */
public class WriteHugeImage {

    public static void main(String[] args) throws IOException, ImageWriteException {

        System.out.println("new Date()");
        System.out.println(new Date());

        // create root path
        Path rootDir = Paths.get("./tiff-trials");
        Files.createDirectories(rootDir);

        // get image path
        Path imagePath = rootDir.resolve("image_" + System.currentTimeMillis() + ".tiff");

        int width = 20000;
        int height = width;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(Color.GREEN);
        g2d.setStroke(new BasicStroke(5));
        int incr = 20;
        for (int i = 0; i < width; i += incr) {
            for (int j = 0; j < height; j += incr) {
                g2d.drawRect(i, j, incr / 2, incr / 2);
            }
        }

        g2d.dispose();

        ImageFormat format = ImageFormats.TIFF;
        final Map<String, Object> optionalParams = new HashMap<>();
        //optionalParams.put(ImagingConstants.PARAM_KEY_VERBOSE, true);
        //optionalParams.put(ImagingConstants.PARAM_KEY_COMPRESSION, TiffConstants.TIFF_COMPRESSION_LZW);

        // image is compressed by default
        optionalParams.put(ImagingConstants.PARAM_KEY_COMPRESSION, TiffConstants.TIFF_COMPRESSION_UNCOMPRESSED);

        Imaging.writeImage(image, imagePath.toFile(), format, optionalParams);
    }

}
