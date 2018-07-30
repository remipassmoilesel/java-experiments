package org.cache;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;

public class JcsCacheImageHelper {

    private final CacheAccess<Long, SerializableImage> cache;

    public JcsCacheImageHelper() {
        cache = JCS.getInstance("default");
    }

    public void put(SerializableImage image) {
        cache.put(image.getId(), image);
    }

    public SerializableImage get(Long id) {
        return cache.get(id);
    }

}
