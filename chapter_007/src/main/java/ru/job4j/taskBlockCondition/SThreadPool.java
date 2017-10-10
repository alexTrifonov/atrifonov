package ru.job4j.taskBlockCondition;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
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
     * Array threads for execute tasks from queue.
     */
    private final MyThread[] threads;

    /**
     * Constructs object SThreadPool. In this object count of threads in array equal count of CPU on computer.
     */
    public SThreadPool() {
        threads = new MyThread[Runtime.getRuntime().availableProcessors()];
        for(MyThread t : threads) {
            t = new MyThread();
            t.start();
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
    private class MyThread extends Thread {
        @Override
        public void run() {
            Runnable r;
            while (!this.isInterrupted()) {
                try {
                    r = queue.take();
                    r.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
