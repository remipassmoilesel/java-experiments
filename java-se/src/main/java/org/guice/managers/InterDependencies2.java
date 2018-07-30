package org.guice.managers;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by remipassmoilesel on 07/11/16.
 */
@Singleton
public class InterDependencies2 {

    @Inject
    static InterDependencies1 dep;

    public InterDependencies2() {

        System.out.println(this.getClass().getName() + " is instancied. " + this);

        // beacause we are using two singletons needing each others,
        // here this dependency can be null and no errors are raised
        System.out.println(dep);

    }
}
