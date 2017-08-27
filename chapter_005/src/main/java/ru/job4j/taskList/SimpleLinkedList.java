package ru.job4j.taskList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class simple collection analogue of list array.
 * @author atrifonov.
 * @since 24.08.2017.
 * @version 1.
 * @param <E> the type of elements in this list.
 */
public class SimpleLinkedList<E> implements SimpleList<E> {
    private ListCell last = new ListCell();
    private ListCell first = new ListCell(last);

    @Override
    public void add(E e) {
        ListCell newCell = new ListCell(e);
        last.itemRef.refNext = newCell;
        last.itemRef = newCell;
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
                }
            }
        }

        return e;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            ListCell prevCell;
            ListCell itListCell = first.refNext;
            private boolean invokeNext = false;
            @Override
            public boolean hasNext() {
                return first.refNext != null && itListCell != null;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    if(prevCell == null) {
                        prevCell = first;
                    } else {
                        prevCell = prevCell.refNext;
                    }

                    E e = itListCell.data;
                    itListCell = itListCell.refNext;

                    invokeNext = true;

                    return e;
                } else {
                    throw new NoSuchElementException();
                }

            }

            @Override
            public void remove() {
                if(invokeNext) {
                    prevCell.refNext = itListCell;
                    invokeNext = false;
                } else {
                    throw new IllegalStateException();
                }
            }
        };
    }

    private class ListCell{
        /**
         * Data in this cell (object for store in SimpleLinkedList)
         */
        private E data;
        /**
         * Reference on next Item.
         */
        private ListCell refNext;
        /**
         * Item that has reference on this element.
         */
        private ListCell itemRef;

        /**
         * Construct empty cell.
         */
        private ListCell(){
        }

        /**
         * Construct cell with reference on next Item.
         * @param refNext item for reference.
         */
        private ListCell(ListCell refNext) {
            ListCell.this.refNext = refNext;
            refNext.itemRef = ListCell.this;
        }

        /**
         * Construct cell with object added in SimpleLinkedList.
         * @param data object for adding.
         */
        private ListCell(E data) {
            ListCell.this.data = data;
        }
    }
}
