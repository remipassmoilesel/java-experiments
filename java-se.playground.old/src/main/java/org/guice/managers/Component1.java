package org.guice.managers;

import javax.inject.Inject;

/**
 * Constructor injection
 */
public class Component1 {

    @Inject
    public Component1(DrawManager dmanager, MapManager mmanager) {

        System.out.println(this.getClass().getName() + " is instancied. " + this);

        // here dependecies are not null
        System.out.println(dmanager);
        System.out.println(mmanager);
    }
}
