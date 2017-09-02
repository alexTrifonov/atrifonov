package ru.job4j.taskSet;

import ru.job4j.taskList.SimpleArrayList;
import java.util.Iterator;


/**
 * Class for container analog set. Container are based on array.
 * @author atrifonov.
 * @since 31.08.2017.
 * @version 2.
 * @param <E> the type of elements in set.
 */
public class SimpleSet<E> implements Iterable<E>{
    /**
     * List for store items.
     */
    private SimpleArrayList<E> list = new SimpleArrayList<>(10);

    /**
     * Add item in set.
     * @param e the item for adding
     */
    public void add(E e) {
        if(!hasE(e)) {
            list.add(e);
        }
    }

    /**
     * Create iterator for set.
     * @return the iterator of inner list.
     */
    public Iterator<E> iterator() {
        return list.iterator();
    }


    /**
     * Check existing the same item.
     * @param e the item that check.
     * @return true if inner array has same item.
     */
    private boolean hasE(E e) {
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
