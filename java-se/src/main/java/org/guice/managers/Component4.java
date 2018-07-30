package org.guice.managers;

import com.google.inject.Inject;

/**
 * Method injection
 */
public class Component4 {

    @Inject
    static DrawManager dmanager;

    @Inject
    static MapManager mmanager;

    public Component4() {

        System.out.println(this.getClass().getName() + " is instancied. " + this);

        // here dependecies are not null
        System.out.println(dmanager);
        System.out.println(mmanager);
    }
}
