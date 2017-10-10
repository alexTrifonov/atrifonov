package ru.job4j.taskBlockCondition;


public class Producer<E> {
    private final SimpleBlockingQueue<E> queue;

    public Producer(SimpleBlockingQueue<E> queue) {
        this.queue = queue;
    }

    public void add(E e) {
        queue.add(e);
    }

}
