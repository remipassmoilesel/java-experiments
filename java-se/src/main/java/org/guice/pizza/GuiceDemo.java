package org.guice.pizza;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by remipassmoilesel on 07/11/16.
 */
public class GuiceDemo {

    public static void main(String[] args) {
    /*
     * Guice.createInjector() takes your Modules, and returns a new Injector
     * instance. Most applications will call this method exactly once, in their
     * main() method.
     */
        Injector injector = Guice.createInjector(new BillingModule());

    /*
     * Now that we've got the injector, we can build objects.
     */
        BillingService billingService = injector.getInstance(BillingService.class);


        billingService.chargeOrder(new PizzaOrder(), new CreditCard());
    }

}
