package org.exceptions;

public class UncaughtExceptionAnalyzer implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e instanceof RuntimeException) {
            System.err.println("Uncaught exception: " + e.getMessage());
            System.exit(1);
        }
    }

}
