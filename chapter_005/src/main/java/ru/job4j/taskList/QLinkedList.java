package ru.job4j.taskList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class linked list for queue.
 * @param <E>
 */
public class QLinkedList<E> implements SimpleList<E> {
    private Cell last = new Cell();
    private Cell first = new Cell();

    public QLinkedList(){
        first.refNext = last;
        last.itemRef = first;
    }

    @Override
    public void add(E e) {
        Cell newCell = new Cell(e);
        newCell.refNext = first.refNext;
        first.refNext = newCell;
        newCell.itemRef = first;
        newCell.refNext.itemRef = newCell;
    }

    @Override
    public E get(int index) {
        E e = null;
        if(index >= 0) {
            Iterator<E> iterator = iterator();
            for(int i = 0; i < index + 1; i++) {
                if(iterator.hasNext()) {
                    e = iterator.next();
                } else {
                    e = null;
                    break;
                }
            }
        }

        return e;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Cell afterCell;
            Cell itListCell = last.itemRef;
            private boolean invokeNext = false;
            @Override
            public boolean hasNext() {

                //return itListCell.refNext != first;
                return itListCell != first;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    if(afterCell == null) {
                        afterCell = last;
                    } else {
                        afterCell = invokeNext ? afterCell.itemRef : afterCell;
                        //afterCell = afterCell.itemRef;
                    }

                    E e = itListCell.data;
                    itListCell = itListCell.itemRef;

                    invokeNext = true;

                    return e;
                } else {
                    throw new NoSuchElementException();
                }

            }

            @Override
            public void remove() {
                if(invokeNext) {
                    afterCell.itemRef = itListCell;
                    itListCell.refNext = afterCell;
                    invokeNext = false;
                } else {
                    throw new IllegalStateException();
                }
            }
        };
    }

    private class Cell {
        /**
         * Data in this cell (object for store in SimpleLinkedList)
         */
        private E data;
        /**
         * Reference on next Item.
         */
        private Cell refNext;
        /**
         * Item that has reference on this element.
         */
        private Cell itemRef;

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
