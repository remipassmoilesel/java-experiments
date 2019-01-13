package org.imaging.compose;

import com.sun.media.jai.codec.TIFFEncodeParam;
import org.geotools.coverage.grid.GeneralGridEnvelope;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.GridGeometry2D;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.TiledImage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.SampleModel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by remipassmoilesel on 03/02/17.
 */
public class Launcher {

    public static void main(String[] args) throws IOException {

        System.out.println("new Date()");
        System.out.println(new Date());

        int tileWidth = 256;
        int tileHeight = 256;

        //here you create a ColorModel for your Image

        /*
        Object source = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        ColorModel cm = new IndexColorModel();

        //then create a compatible SampleModel, with the tilesize
        SampleModel sm = cm.createCompatibleSampleModel(tileWidth, tileHeight);

        TiledImage image = new TiledImage(0, 0, 20000, 20000, 0, 0, sm, cm);


        TIFFEncodeParam tep = new TIFFEncodeParam();
        //important to avoid OutOfMemory
        tep.setTileSize(tileWidth, tileHeight);
        tep.setWriteTiled(true);
        //fast compression
        tep.setCompression(TIFFEncodeParam.COMPRESSION_PACKBITS);
        //write file

        JAI.create("filestore", image, filepath, "TIFF", tep);


        PlanarImage plImg = PlanarImage.wrapRenderedImage(new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB));
        GridCoverage2D grid = new GridCoverage2D(
                "new",
                image,
                new GridGeometry2D(new GeneralGridEnvelope(new Rectangle(0, 0, (int) width, (int) height)), envelope),

        );
        */


    }

    public void compose() throws IOException {

        Path rootDir = Paths.get("data/arbitrary-pictures-2");

        int maxWidth = 5000;
        int maxHeight = maxWidth;

        // create an empty png
        Path png = Paths.get("data/huge_" + System.currentTimeMillis() + ".png");
        PngComposer pngComposer = new PngComposer(png);
        pngComposer.createEmpty(maxWidth, maxHeight);

        // list all jpg pictures and add them
        int w = 0;
        int h = 0;
        int mh = 0;
        for (Path p : Files.newDirectoryStream(rootDir)) {
            System.out.println(p);

            BufferedImage img = ImageIO.read(p.toFile());

            pngComposer.addImage(img, new Point(w, h));

            // get maximum height of row
            if (mh < img.getHeight()) {
                mh = img.getHeight();
            }

            // moving forward on row
            w += img.getWidth();

            // we are at the end of row
            if (w > maxWidth) {
                w = 0;
                h += mh;
                mh = 0;
            }
        }

    }

}
