package ru.job4j.taskSynchronize;

import net.jcip.annotations.ThreadSafe;
import ru.job4j.taskList.*;

import java.util.Iterator;

@ThreadSafe
public class SynchronizedSmpArrList<E> extends SimpleArrayList<E>{
    public SynchronizedSmpArrList(int capacity){
        super(capacity);
    }

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
    public Object[] getContainer() {
        synchronized (this) {
            return super.getContainer();
        }
    }

    @Override
    public int getLastIndex() {
        synchronized (this) {
            return super.getLastIndex();
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
