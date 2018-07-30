package org.imaging.compose;

import ar.com.hjg.pngj.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by remipassmoilesel on 03/02/17.
 */
public class PngComposer {

    private Path imagePath;
    private ImageInfo imageInfo;

    public PngComposer(Path p) {
        this.imagePath = p;
    }

    public void addImage(BufferedImage image, Point position) throws IOException {

        if (imageInfo == null) {
            readImageInfo();
        }

        PngWriter pngw = new PngWriter(imagePath.toFile(), imageInfo, true);

        // iterate pixels of image to write
        Raster raster = image.getData();

        int width = raster.getWidth();
        int height = raster.getHeight();
        int bands = raster.getNumBands();
        int[] row = new int[width * 1 * bands];

        System.out.println("image.getWidth()");
        System.out.println(image.getWidth());
        System.out.println(raster.getWidth());

        int desty = position.y;

        for (int y = 0; y < height - 1; y++) {

            // read a raster row
            raster.getPixels(0, y, width, 1, row);

            // create png writer row
            ImageLineInt pngrow = new ImageLineInt(imageInfo, row);

            // print line
            pngw.writeRow(pngrow, desty);
            desty++;
        }

        IImageLine l1 = new ImageLineInt(imageInfo);
        pngw.writeRow(l1, imageInfo.rows);
        pngw.end();

    }

    /**
     * @param width
     * @param height
     */
    public void createEmpty(int width, int height) throws IOException {

        Files.deleteIfExists(imagePath);

        try {

            ImageInfo info = new ImageInfo(width, height, 16, true);
            PngWriter pngw = new PngWriter(imagePath.toFile(), info, true);

            // write empty lines
            for (int i = 0; i < height; i++) {
                IImageLine l1 = new ImageLineInt(info);
                pngw.writeRow(l1);
            }

            pngw.end();

            this.imageInfo = info;

        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public void readImageInfo() throws IOException {
        try {
            PngReader reader = new PngReader(imagePath.toFile());
            imageInfo = reader.imgInfo;
            reader.close();
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    /**
     * Read specified images and write them at specified position
     *
     * @param images
     * @param positions
     */
    public void addImagePaths(List<Path> images, List<Point> positions) {

        // check each picture have a position
        if (images.size() != positions.size()) {
            throw new IllegalArgumentException("Images and positions numbers are different: "
                    + images.size() + " / " + positions.size());
        }

        // iterate pictures
        for (int i = 0; i < images.size(); i++) {
            BufferedImage img = null;
            try {
                img = ImageIO.read(images.get(i).toFile());
                Point pt = positions.get(i);
                addImage(img, pt);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Read specified images and write them at specified position
     *
     * @param images
     * @param positions
     */
    public void addImages(List<BufferedImage> images, List<Point> positions) throws IOException {

        // check each picture have a position
        if (images.size() != positions.size()) {
            throw new IllegalArgumentException("Images and positions numbers are different: "
                    + images.size() + " / " + positions.size());
        }

        // iterate pictures
        for (int i = 0; i < images.size(); i++) {
            BufferedImage img = images.get(i);
            Point pt = positions.get(i);
            addImage(img, pt);
        }

    }


}
