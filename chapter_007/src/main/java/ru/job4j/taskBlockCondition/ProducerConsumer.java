package ru.job4j.taskBlockCondition;


import java.util.LinkedList;

/**
 * Class for pattern ProducerConsumer
 */
public class ProducerConsumer<E> {
    private LinkedList<E> blockQueue = new LinkedList<>();

    public void add(E e) {
        synchronized (blockQueue) {
            this.blockQueue.add(e);
            this.blockQueue.notifyAll();
        }
    }

    public E poll() {
        synchronized (this.blockQueue) {
            while (this.blockQueue.isEmpty()) {
                try {
                    this.blockQueue.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            return blockQueue.pollFirst();
        }
    }


}
