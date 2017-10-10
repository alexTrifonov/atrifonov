package ru.job4j.taskBlockCondition;


public class Consumer<E> {
    private final SimpleBlockingQueue<E> queue;

    public Consumer(SimpleBlockingQueue<E> queue) {
        this.queue = queue;
    }

    public E poll() {
        E item = null;
        try {
            item = queue.poll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return item;
    }
}
