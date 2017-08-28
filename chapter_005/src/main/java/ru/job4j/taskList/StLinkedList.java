package ru.job4j.taskList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for linked list for stack.
 * @author atrifonov.
 * @since 28.08.2017.
 * @version 1.
 * @param <E> the type of element in this list
 */
public class StLinkedList<E> implements SimpleList<E> {
    /**
     * Sentinel.
     */
    private Cell first = new Cell();

    @Override
    public void add(E e) {
        Cell newCell = new Cell(e);
        if(first.refNext == null) {
            first.refNext = newCell;
        } else {
            newCell.refNext = first.refNext;
            first.refNext = newCell;
        }
    }

    @Override
    public E get(int index) {
        E e = null;
        if(index >= 0) {
            Iterator<E> it = iterator();
            for(int i = 0; i < index + 1; i++) {
                e = it.hasNext() ? it.next() : null;
                if(e == null) {
                    break;
                }
            }
        }
        return e;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Cell itCell = first.refNext;
            private Cell prevCell;
            private boolean invokeNext = false;
            @Override
            public boolean hasNext() {
                return itCell != null;
            }

            @Override
            public E next() {
                if(hasNext()) {
                    if(prevCell == null) {
                        prevCell = first;
                    } else {
                        prevCell = invokeNext ? prevCell.refNext : prevCell;
                    }
                    E e = itCell.data;
                    itCell = itCell.refNext;
                    invokeNext = true;
                    return e;
                } else {
                    throw new NoSuchElementException();
                }
            }

            @Override
            public void remove() {
                if(invokeNext) {
                    prevCell.refNext = itCell;
                    invokeNext = false;
                } else throw new IllegalStateException();
            }
        };
    }

    private class Cell{
        /**
         * Data in this cell (object for store in SimpleLinkedList)
         */
        private E data;
        /**
         * Reference on next Item.
         */
        private Cell refNext;

        /**
         * Construct empty cell.
         */
        private Cell(){
        }

        /**
         * Construct cell with object added in SimpleLinkedList.
         * @param data object for adding.
         */
        private Cell(E data) {
            Cell.this.data = data;
        }
    }

}

