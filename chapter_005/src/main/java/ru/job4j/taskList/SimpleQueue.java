package ru.job4j.taskList;

import java.util.Iterator;

/**
 * Class for simple queue based on linked list.
 * @author atrifonov.
 * @since 28.08.2017.
 * @version 1.
 * @param <E> the type of elements in queue.
 */
public class SimpleQueue<E> {
    private QLinkedList<E> list = new QLinkedList<E>();

    public void enqueue(E e) {
        list.add(e);
    }

    public E dequeue(){
        E e;
        Iterator<E> it = list.iterator();
        if(it.hasNext()) {
            e = it.next();
            it.remove();
            return e;
        } else {
            throw new EmptySimpleQueueException();
        }
    }
}
