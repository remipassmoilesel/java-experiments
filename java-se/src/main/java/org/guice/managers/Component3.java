package org.guice.managers;

import javax.inject.Inject;

/**
 * Method injection
 */
public class Component3 {

    DrawManager dmanager;

    MapManager mmanager;

    public Component3() {

        System.out.println(this.getClass().getName() + " is instancied. " + this);

        // here dependecies are null, they will be injected after constructor call
        System.out.println(dmanager);
        System.out.println(mmanager);
    }

    @Inject
    public void setDmanager(DrawManager dmanager) {
        this.dmanager = dmanager;
    }

    @Inject
    public void setMmanager(MapManager mmanager) {
        this.mmanager = mmanager;
    }

    public DrawManager getDmanager() {
        return dmanager;
    }

    public MapManager getMmanager() {
        return mmanager;
    }
}
