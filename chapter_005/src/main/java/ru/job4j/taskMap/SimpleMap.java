package ru.job4j.taskMap;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for simple map.
 * @author atrifonov.
 * @since 06.09.2017.
 * @version 1.
 * @param <T> the type of key.
 * @param <V> the type of value.
 */
public class SimpleMap<T, V> implements Iterable<T> {
    private Object[] store;


    public SimpleMap(int size) {
        store = new Object[size];
    }

    public boolean insert(T key, V value) {
        boolean successfully = false;
        if(key != null) {
            int index = hash(key);
            if(store[index] == null) {
                store[index] = new Bucket(key, value);
                successfully = true;
            }
        }

        return successfully;
    }
    public V get(T key) {
        V value = null;
        if(key != null) {
            int index = hash(key);
            if(index < store.length && store[index] != null) {
                value = ((Bucket) store[index]).key.equals(key) ?  ((Bucket) store[index]).value : null;
            }
        }
        return value;
    }
    public boolean delete(T key){
        boolean successfully = false;
        if(key != null) {
            int index = hash(key);
            if(index < store.length && store[index] != null) {
                if(((Bucket) store[index]).key.equals(key)) {
                    store[index] = null;
                    successfully = true;
                }

            }
        }
        return successfully;
    }
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int position = 0;
            private boolean invokeNext;
            @Override
            public boolean hasNext() {
                boolean has = false;
                for(int i = position; i < store.length; i++) {
                    if (store[i] != null) {
                        has = true;
                        position = i;
                        break;
                    }
                }
                return has;
            }

            @Override
            public T next() {
                if(hasNext()) {
                    invokeNext = true;
                    return ((Bucket) store[position++]).key;
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                if(invokeNext) {
                    store[position - 1] = null;
                } else {
                    throw new  IllegalStateException();
                }
            }
        };
    }

    private int hash(T key) {
        return key.hashCode() % store.length;
    }

    private class Bucket {
        T key;
        V value;
        Bucket(T k, V v) {
            this.key = k;
            this.value = v;
        }
    }
}
