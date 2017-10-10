package ru.job4j.taskBlockCondition;


import java.util.LinkedList;

/**
 * Class for simple blocking queue.
 */
public class SimpleBlockingQueue<E> {
    private LinkedList<E> blockQueue = new LinkedList<>();

    public void add(E e) {
        synchronized (blockQueue) {
            this.blockQueue.add(e);
            this.blockQueue.notifyAll();
        }
    }

    public E poll() throws InterruptedException {
        synchronized (this.blockQueue) {
            while (this.blockQueue.isEmpty()) {
                this.blockQueue.wait();
            }
            return blockQueue.pollFirst();
        }
    }


}
