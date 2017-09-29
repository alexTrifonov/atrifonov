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
        synchronized (this) {
            return super.iterator();
        }
    }
}
