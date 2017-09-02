package ru.job4j.taskSet;

import ru.job4j.taskList.QLinkedList;

import java.util.Iterator;

/**
 * Class simple set based on linked list.
 * @author atrifonov.
 * @since 02.09/2017.
 * @version 1.
 * @param <E> the type of stored items.
 */
public class LinkedSet<E> implements Iterable<E>{
    /**
     * List for store items.
     */
    private QLinkedList<E> list = new QLinkedList<>();


    public void add(E e) {
        if(!hasItem(e)) {
            this.list.add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }

    /**
     * Check existing same item.
     * @param e item for find.
     * @return true if set yet has e.
     */
    private boolean hasItem(E e) {
        boolean has = false;
        for(E findItem : this) {
            if(findItem.equals(e)){
                has = true;
                break;
            }
        }

        return has;
    }
}
