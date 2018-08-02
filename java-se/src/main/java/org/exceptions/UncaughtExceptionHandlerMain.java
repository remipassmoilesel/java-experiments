package org.exceptions;

/**
 * This method can be useful to terminate a process in a container for a specific error,
 * in order to make application restart
 */
public class UncaughtExceptionHandlerMain {

    public static void main(String[] args) throws Exception {

        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionAnalyzer());

        keepProcessAlive10seconds();

        try {
            throw new RuntimeException("Will not terminate process");
        } catch (Exception e) {
            System.err.println("Exception caught: " + e.getMessage());
        }

        throw new RuntimeException("Will terminate process");
    }

    private static void keepProcessAlive10seconds() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Waiting 10s ...");
                    Thread.sleep(10000l);
                    System.out.println("Terminated !");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
