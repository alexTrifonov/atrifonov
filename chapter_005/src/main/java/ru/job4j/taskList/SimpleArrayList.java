package ru.job4j.taskList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class simple collection analogue of list array.
 * @author atrifonov.
 * @since 24.08.2017.
 * @version 1.
 * @param <E> the type of elements in this list.
 */
public class SimpleArrayList<E> implements SimpleList<E> {
    /**
     * Array for store objects.
     */
    private Object[] container;
    /**
     * Current index for adding object.
     */
    private int lastIndex;

    /**
     * Construct SimpleArrayList with initial capacity inner array.
     * @param capacity initial length of inner array.
     */
    public SimpleArrayList (int capacity) {
        container = new Object[capacity];
    }

    @Override
    public void add(E e) {
        if(lastIndex < container.length) {
            container[lastIndex++] = e;
        } else {
            container = Arrays.copyOf(container, container.length + container.length / 2);
            container[lastIndex++] = e;
        }
        Object[] temp = Arrays.copyOf(container, lastIndex);
        Arrays.sort(temp);
        container = Arrays.copyOf(temp, container.length);
    }

    @Override
    public E get(int index) {
        E e = null;
        if (index < lastIndex) {

            e = (E) container[index];
        }
        return e;
    }

    public Object[] getContainer() {
        return container;
    }

    public int getLastIndex() {
        return lastIndex;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int iterIndex;
            private boolean invokeNext;
            @Override
            public boolean hasNext() {

                return iterIndex < lastIndex;
            }

            @Override
            public E next() {
                if(hasNext()) {
                    invokeNext = true;
                    return (E) container[iterIndex++];
                }else {
                    throw new  NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                if(invokeNext) {
                    int length = SimpleArrayList.this.container.length;
                    Object[] newContainer = new Object[length - 1];
                    System.arraycopy(SimpleArrayList.this.container, 0, newContainer, 0, iterIndex - 1);
                    System.arraycopy(SimpleArrayList.this.container, iterIndex, newContainer, iterIndex - 1, length - iterIndex - 1);
                    iterIndex--;
                    SimpleArrayList.this.lastIndex--;
                    SimpleArrayList.this.container = newContainer;
                    invokeNext = false;
                } else {
                    throw new IllegalStateException();
                }
            }
        };


    }


}

