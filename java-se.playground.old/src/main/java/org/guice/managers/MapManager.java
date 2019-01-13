package org.guice.managers;

import com.google.inject.Singleton;

import javax.inject.Inject;

/**
 * Here @Singleton prevent Guice to create a new instance at each call
 */
@Singleton
public class MapManager {

    @Inject
    DrawManager dmanager;

    @Inject
    static DrawManager dmanagerNonNull;

    public MapManager() {
        System.out.println(this.getClass().getName() + " is instancied. " + this);

        // null for now, non null after constructor call
        System.out.println(dmanager);

        // non null
        System.out.println(dmanagerNonNull);
    }
}
