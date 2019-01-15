package org.guice.pizza;

import com.google.inject.Inject;

class BillingService {
    private final CreditCardProcessor processor;
    private final TransactionLog transactionLog;

    @Inject
    BillingService(CreditCardProcessor processor,
                   TransactionLog transactionLog) {

        System.out.println(this.getClass().getName() + " is instancied");

        this.processor = processor;
        this.transactionLog = transactionLog;
    }

    public Receipt chargeOrder(PizzaOrder order, CreditCard creditCard) {

        transactionLog.log("Charging order: " + order + " / " + creditCard);

        processor.processCard(creditCard);

        return new Receipt(order, creditCard);
    }
}

