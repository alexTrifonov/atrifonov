package ru.job4j.taskBlockCondition;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Class for thread pool.
 * @author atrifonov.
 * @since 09.10.2017.
 * @version 1.
 */
public class SThreadPool {
    /**
     * Thread safe queue for adding and getting objects Runnable.
     */
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    /**
     * Constructs object SThreadPool. In this object count of threads in array equal count of CPU on computer.
     */
    public SThreadPool() {
        for(int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            new SThread().start();
        }
    }

    /**
     * Add not null object Runnable to queue.
     * @param r object for execute.
     */
    public void add(Runnable r) {
        if (r != null) {
            try {
                queue.put(r);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Nested class is descendent of class Thread. Class for array threads.
     */
    private class SThread extends Thread {
        @Override
        public void run() {
            while (!this.isInterrupted()) {
                try {
                    queue.take().run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
