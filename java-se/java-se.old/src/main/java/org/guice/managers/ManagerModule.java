package org.guice.managers;

import com.google.inject.AbstractModule;

public class ManagerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DrawManager.class);
        bind(MapManager.class);

        // mandatory to inject static fields
        requestStaticInjection(MapManager.class);
        requestStaticInjection(Component4.class);

        requestStaticInjection(InterDependencies1.class);
        requestStaticInjection(InterDependencies2.class);
    }
}