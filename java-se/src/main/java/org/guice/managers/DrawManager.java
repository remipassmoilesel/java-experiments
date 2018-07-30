package org.guice.managers;

import com.google.inject.Singleton;

import javax.inject.Inject;

/**
 * Here @Singleton prevent Guice to create a new instance at each call
 */
@Singleton
public class DrawManager {

    public DrawManager() {
        System.out.println(this.getClass().getName() + " is instancied. " + this);
    }
}
