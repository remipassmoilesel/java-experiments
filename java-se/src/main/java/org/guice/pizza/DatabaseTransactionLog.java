package org.guice.pizza;

/**
 * Created by remipassmoilesel on 07/11/16.
 */
public class DatabaseTransactionLog implements TransactionLog {

    public DatabaseTransactionLog() {
        System.out.println(this.getClass().getName() + " is instancied");
    }

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
