package org.guice.pizza;

/**
 * Created by remipassmoilesel on 07/11/16.
 */
public class Receipt {
    public Receipt() {
    }

    public Receipt(PizzaOrder order, CreditCard card) {
        System.out.println(this.getClass().getName() + " is instancied");
    }
}
