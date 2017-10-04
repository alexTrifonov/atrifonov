package ru.job4j.taskSynchronize;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.taskList.SimpleLinkedList;

import java.util.Iterator;

@ThreadSafe
public class SynchronizedSmpLinkList<E> extends SimpleLinkedList<E>{
    @Override
    public void add(E e) {
        synchronized (this) {
            super.add(e);
        }
    }

    @Override
    public E get(int index) {
        synchronized (this) {
            return super.get(index);
        }
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it =  super.iterator();
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                synchronized (this) {
                    return it.hasNext();
                }
            }

            @Override
            public E next() {
                synchronized (this) {
                    return it.next();
                }
            }

            @Override
            public void remove() {
                synchronized (this) {
                    it.remove();
                }
            }
        };
    }
}
