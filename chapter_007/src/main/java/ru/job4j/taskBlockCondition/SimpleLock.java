package ru.job4j.taskBlockCondition;

/**
 * Class implementation Lock.
 */
public class SimpleLock {
    private boolean blocked = false;

    public synchronized void lock() throws InterruptedException {
        while (blocked) {
            wait();
        }
        blocked = true;
    }

    public synchronized void unlock() {
        blocked = false;
        notifyAll();
    }


}
