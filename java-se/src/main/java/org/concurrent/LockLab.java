package org.concurrent;

import java.util.concurrent.RunnableFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by remipassmoilesel on 26/10/16.
 */
public class LockLab implements Runnable {

    public static final Lock lock = new ReentrantLock();
    private final int intervalMs;
    private final int tours;
    private final String name;

    public static void main(String[] args) {
        new LockLab("A", 1000, 15);
        new LockLab("B", 1200, 15);
        new LockLab("C", 1500, 15);
    }

    /**
     * Create a named lock test. This will keep lock several tour for a determined time in ms.
     *
     * @param name
     */
    public LockLab(String name, int intervalMs, int tours) {

        this.name = name;
        this.intervalMs = intervalMs;
        this.tours = tours;

        new Thread(this).start();

    }

    private void waitInterval() {
        try {
            Thread.sleep(intervalMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        int count = 0;
        while (count < tours) {

            String msg = this.name + " process. ";
            boolean owner = false;

            if (lock.tryLock()) {
                lock.lock();
                owner = true;
                msg += "I am the owner of the lock.";
            } else {
                msg += "I can't own the lock for now.";
            }

            System.out.println(msg);
            waitInterval();

            if (owner) {
                lock.unlock();
            }

            count++;

        }

        System.out.println(this.name + " process finished.");

    }
}
