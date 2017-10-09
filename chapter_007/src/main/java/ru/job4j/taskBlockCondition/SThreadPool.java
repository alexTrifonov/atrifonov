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
    BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    MyThread[] threads;
    Runnable nRun;
    public SThreadPool() {
        threads = new MyThread[Runtime.getRuntime().availableProcessors()];
        for(MyThread t : threads) {
            t = new MyThread();
            t.start();
        }
    }

    public void add(Runnable r) {
        boolean free = false;
        for(MyThread t : threads) {
            if(t.isThreadFree()) {
                free = true;
                nRun = r;
                break;
            }
        }
        if (!free) {
            try {
                queue.put(r);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private class MyThread extends Thread {
        private boolean threadFree = true;
        @Override
        public void run() {
            Runnable r;
            while (this.isInterrupted()) {
                threadFree = false;
                if (nRun != null) {
                    r = nRun;
                    r.run();
                    nRun = null;
                } else {
                    try {
                        r = queue.take();
                        r.run();

                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                    }
                }
                threadFree = true;
            }
        }

        public boolean isThreadFree() {
            return threadFree;
        }
    }

}
