package org.imaging;

import ar.com.hjg.pngj.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.SinglePixelPackedSampleModel;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by remipassmoilesel on 03/02/17.
 */
public class PngjLab {

    public static void main(String[] args) {

        System.out.println("new Date()");
        System.out.println(new Date());
        System.out.println();

        Path dest = Paths.get("data/image2.png");

        /*
        int width = 20000;
        int height = width;

        ImageInfo info = new ImageInfo(width, height, 8, true);
        PngWriter pngw = new PngWriter(dest.toFile(), info, true);

        pngw.getMetadata().setText(PngChunkTextVar.KEY_Description, "Hey hey !");
        pngw.getMetadata().setDpi(100.0);
        pngw.getMetadata().setTimeNow(0); // 0 seconds fron now = now
        pngw.getMetadata().setText(PngChunkTextVar.KEY_Title, "just a text image");
        pngw.getMetadata().setText("my key", "my text");

        for (int i = 0; i < width; i++) {
            IImageLine l1 = new ImageLineInt(info);
            pngw.writeRow(l1);
        }

        pngw.end();

        */
        PngReader pngr = new PngReader(dest.toFile());
        System.out.println(pngr);

        int cols = pngr.imgInfo.cols;
        int channels = pngr.imgInfo.channels;
        int rowLength = cols * channels;

        int i = 0;
        while (pngr.hasMoreRows() && i < 50) {
            IImageLine row = pngr.readRow();

            // line content is for 3 channel: R G B R G B R G B ...
            // with alpha value: R G B A R G B A R G B A ...
            int[] content = ((ImageLineInt) row).getScanline();

            System.out.println(content.length);
            System.out.println(content.length == rowLength);

            /*
            System.out.println(row);
            System.out.println("content");
            for (int j = 0; j < content.length; j++) {
                System.out.println(content[j]);
            }

            ImageLineHelper.setPixelRGB8();
            */

            i++;
        }

    }

    /**
     * -
     * - @param bi BufferedImage of TYPE_INT_ARGB or TYPE_INT_RGB
     * - @param os
     **/
    public static void writeARGB(BufferedImage bi, OutputStream os) {

        if (bi.getType() != BufferedImage.TYPE_INT_ARGB)
            throw new PngjException("This method expects  BufferedImage.TYPE_INT_ARGB");

        ImageInfo imi = new ImageInfo(bi.getWidth(), bi.getHeight(), 8, true);
        PngWriter pngw = new PngWriter(os, imi);

        // pngw.setCompLevel(6); // tuning
        // pngw.setFilterType(FilterType.FILTER_PAETH); // tuning
        DataBufferInt db = ((DataBufferInt) bi.getRaster().getDataBuffer());
        if (db.getNumBanks() != 1) throw new PngjException("This method expects one bank");
        SinglePixelPackedSampleModel samplemodel = (SinglePixelPackedSampleModel) bi.getSampleModel();
        ImageLineInt line = new ImageLineInt(imi);
        int[] dbbuf = db.getData();
        for (int row = 0; row < imi.rows; row++) {
            int elem = samplemodel.getOffset(0, row);
            for (int col = 0, j = 0; col < imi.cols; col++) {
                int sample = dbbuf[elem++];
                line.getScanline()[j++] = (sample & 0xFF0000) >> 16; // R
                line.getScanline()[j++] = (sample & 0xFF00) >> 8; // G
                line.getScanline()[j++] = (sample & 0xFF); // B
                line.getScanline()[j++] = (((sample & 0xFF000000) >> 24) & 0xFF); // A
            }
            pngw.writeRow(line, row);
        }

    }

}
