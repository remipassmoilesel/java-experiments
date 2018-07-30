package org.cache;

import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class EhCacheStringCacheHelper {

    private final org.ehcache.CacheManager cacheManager;
    private final String cacheName;

    public EhCacheStringCacheHelper(String cacheName){
        this.cacheName = cacheName;

        CacheConfigurationBuilder<Long, String> cacheConfig = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.heap(10));

        this.cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                                    .withCache(cacheName, cacheConfig).build();
        cacheManager.init();
    }

    public Cache<Long, String> getCache(){
        return cacheManager.getCache(cacheName, Long.class, String.class);
    }

    public void put(Long k, String v){
        getCache().put(k,v);
    }

    public void close() throws Throwable {
        cacheManager.close();
    }
}
