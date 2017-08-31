package ru.job4j.taskSet;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for container analog set. Container are based on array.
 * @author atrifonov.
 * @since 31.08.2017.
 * @version 1.
 * @param <E> the type of elements in set.
 */
public class SimpleSet<E> implements Iterable<E>{
    /**
     * Inner array for store items.
     */
    private Object[] container;
    /**
     * Next position for add item.
     */
    private int position;

    /**
     * Construct SimpleSet with inner array length 10 items.
     */
    public SimpleSet() {
        container = new Object[10];
    }

    /**
     * Construct SimpleSet with inner array length si.
     * @param size the length of inner array.
     */
    public SimpleSet(int size) {
        container = new Object[size];
    }

    /**
     * Add item in SimpleSet.
     * @param e item for adding.
     */
    public void add(E e) {
        if (!hasE(e)) {
            if(position == container.length) {
                container = Arrays.copyOf(container, container.length + container.length/2);
                container[position++] = e;
            } else {
                container[position++] = e;
            }

        }
    }

    /**
     * Iterator.
     * @return the iterator.
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int index;
            boolean invokeNext;
            @Override
            public boolean hasNext() {
                boolean has = false;
                if(index < position) {
                    has = true;
                }
                return has;
            }

            @Override
            public E next() {
                if(hasNext()) {
                    invokeNext = true;
                    return (E) container[index++];
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                if(invokeNext) {
                    Object[] newContainer = new Object[container.length];
                    System.arraycopy(container, 0, newContainer, 0, index - 1);
                    System.arraycopy(container, index, newContainer, index - 1, container.length - index );
                    container = newContainer;
                    invokeNext = false;
                    position--;
                    index--;
                } else {
                    throw new IllegalStateException();
                }
            }
        };
    }

    /**
     * Check existing the same item.
     * @param e the item that check.
     * @return true if inner array has same item.
     */
    private boolean hasE(E e) {
        boolean hasItem = false;
        for(int i = 0; i < position; i++) {
            E anItem = (E) container[i];
            if (anItem.equals(e)) {
                hasItem = true;
                break;
            }
        }
        return hasItem;
    }
}
