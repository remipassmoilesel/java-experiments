package org.guice.managers;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.guice.pizza.BillingModule;

/**
 * Created by remipassmoilesel on 07/11/16.
 */
public class GuiceDemo {
    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new ManagerModule());

//        injector.getInstance(DrawManager.class);
//        injector.getInstance(MapManager.class);

        // no dependency injection
        //Component1 comp1 = new Component1();

        System.out.println();
        System.out.println("--- injector.getInstance(Component1.class)");
        System.out.println(injector.getInstance(Component1.class));

        System.out.println();
        System.out.println("--- injector.getInstance(Component2.class)");
        Component2 c2 = injector.getInstance(Component2.class);
        System.out.println(c2);
        System.out.println(c2.getDmanager());
        System.out.println(c2.getMmanager());

        System.out.println();
        System.out.println("--- injector.getInstance(Component3.class)");
        Component3 c3 = injector.getInstance(Component3.class);
        System.out.println(c3);
        System.out.println(c3.getDmanager());
        System.out.println(c3.getMmanager());

        System.out.println();
        System.out.println("--- injector.getInstance(Component4.class)");
        Component4 c4 = injector.getInstance(Component4.class);
        System.out.println(c4);

        System.out.println();
        System.out.println();

        // Here two classes need each other, dependencies will be null and
        // no errors are raised
        injector.getInstance(InterDependencies1.class);
        injector.getInstance(InterDependencies2.class);

    }
}
