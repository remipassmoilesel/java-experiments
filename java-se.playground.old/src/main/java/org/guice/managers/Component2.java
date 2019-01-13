package org.guice.managers;

import com.google.inject.Inject;

/**
 * Field injection
 */
public class Component2 {

    @Inject
    DrawManager dmanager;

    @Inject
    MapManager mmanager;

    public Component2() {

        System.out.println(this.getClass().getName() + " is instancied. " + this);

        // here dependecies are null, they will be injected after constructor call
        System.out.println(dmanager);
        System.out.println(mmanager);
    }

    public MapManager getMmanager() {
        return mmanager;
    }

    public DrawManager getDmanager() {
        return dmanager;
    }
}
