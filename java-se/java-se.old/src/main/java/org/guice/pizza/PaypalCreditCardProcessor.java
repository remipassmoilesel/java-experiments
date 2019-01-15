package org.guice.pizza;

/**
 * Created by remipassmoilesel on 07/11/16.
 */
public class PaypalCreditCardProcessor implements CreditCardProcessor {
    public PaypalCreditCardProcessor() {
        System.out.println(this.getClass().getName() + " is instancied");
    }

    @Override
    public void processCard(CreditCard creditCard) {
        System.out.println("Processing card: " + creditCard);
    }
}
