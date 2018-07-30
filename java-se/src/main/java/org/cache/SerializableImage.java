package org.cache;

import javax.imageio.ImageIO;
import javax.persistence.Transient;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.nio.file.Files;
import java.nio.file.Path;

public class SerializableImage implements Serializable{

    private Long id;

    private transient WeakReference<BufferedImage> bufferedImage;

    private final byte[] data;

    public SerializableImage(Long id, Path imagePath) throws IOException {
        this.id = id;
        bufferedImage = new WeakReference<>(ImageIO.read(Files.newInputStream(imagePath)));
        data = imageToBytes(bufferedImage.get());

    }

    public BufferedImage getBufferedImage() {
        if(bufferedImage == null || bufferedImage.get() == null){
            bufferedImage = new WeakReference<>(bytesToImage(data));
        }
        return bufferedImage.get();
    }

    public Long getId() {
        return id;
    }

    /**
     * Return a byte array or null if an error occur
     *
     * @param img
     * @return
     */
    public static byte[] imageToBytes(BufferedImage img) {

        try {
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                ImageIO.write(img, "png", out);
                return out.toByteArray();
            }
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }

    }

    /**
     * Return a buffered image generated from byte array or null if an error occur
     *
     * @param bytes
     * @return
     */
    public static BufferedImage bytesToImage(byte[] bytes) {

        try {
            try (ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
                return ImageIO.read(in);
            }
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }

    }

}
