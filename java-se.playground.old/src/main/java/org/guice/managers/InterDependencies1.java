package org.guice.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by remipassmoilesel on 07/11/16.
 */
@Singleton
public class InterDependencies1 {

    @Inject
    static InterDependencies2 dep;

    public InterDependencies1() {

        System.out.println(this.getClass().getName() + " is instancied. " + this);

        // beacause we are using two singletons needing each others,
        // here this dependency can be null and no errors are raised
        System.out.println(dep);

    }
}
