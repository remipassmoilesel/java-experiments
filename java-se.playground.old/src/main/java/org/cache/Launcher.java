package org.cache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Launcher {

    public static void main(String[] args) throws Throwable {

        System.out.println("This example should be run with -Xmx50m to be relevant");

        // ehStringCache();

        // outOfMemory();

        jcsImageCache();

    }

    private static void jcsImageCache() throws Exception {

        Path imageDirectory = Paths.get("data/arbitrary-pictures-2");
        if(Files.isDirectory(imageDirectory) == false){
            throw new Exception("Image directory does not exist");
        }

        JcsCacheImageHelper cache = new JcsCacheImageHelper();

        long l = 0;
        for (Path i : Files.newDirectoryStream(imageDirectory)) {
            System.out.println(String.format("Processing: id=%d, path=%s", l, i));
            cache.put(new SerializableImage(l, i));
            l++;
        }

        for (long i = 0; i < l; i++) {
            SerializableImage img = cache.get(i);

            if(img == null){
                System.err.println(String.format("Image null for id %d", i));
            }
            else {
                Long id = img.getId();

                int w = img.getBufferedImage().getWidth();
                int h = img.getBufferedImage().getHeight();
                System.out.println(String.format("Image %d: %d x %d", id, w, h));
            }
        }

    }

    /**
     * Throw heap space error with 64mb of heap space
     * @throws Exception
     */
    private static void outOfMemory() throws Exception {

        Path imageDirectory = Paths.get("data/arbitrary-pictures-2");
        if(Files.isDirectory(imageDirectory) == false){
            throw new Exception("Image directory does not exist");
        }

        ArrayList<SerializableImage> images = new ArrayList<>();

        long l = 0;
        for (Path i : Files.newDirectoryStream(imageDirectory)) {
            System.out.println("Processing: " + i);
            images.add(new SerializableImage(l, i));
            l++;
        }

    }

    private static void ehStringCache() throws Throwable {
        EhCacheStringCacheHelper sce = new EhCacheStringCacheHelper("cache1");
        sce.getCache().put(1l, "value1");
        sce.getCache().put(2l, "value2");
        System.out.println(sce.getCache().get(1l));

        sce.close();
    }
}
