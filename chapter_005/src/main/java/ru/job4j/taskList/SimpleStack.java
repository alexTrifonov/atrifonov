package ru.job4j.taskList;

import java.util.Iterator;

/**
 * Class for primitive stack.
 * @author atrifonov.
 * @since 28.08.2017.
 * @version 1
 * @param <E> the type of elements in stack.
 */
public class SimpleStack<E> {
    private StLinkedList<E> list = new StLinkedList<>();

    public E push(E item) {
        list.add(item);
        return item;
    }

    public E pop() {
        E e;
        Iterator<E> it = list.iterator();
        if(it.hasNext()) {
            e = it.next();
            it.remove();
            return e;
        } else {
            throw new EmptySmStackException();
        }
    }
}
