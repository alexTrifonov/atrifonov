package ru.job4j.taskSet;

import ru.job4j.taskList.SimpleArrayList;
import java.util.Arrays;
import java.util.Iterator;




/**
 * Created by Alexandr on 02.09.2017.
 */
public class FastSet<E extends Comparable<? super E>> implements Iterable<E> {
    /**
     * List for store items.
     */
    SimpleArrayList<E> list = new SimpleArrayList<>(10);

    /**
     * Add item in set.
     * @param e the item for adding
     */
    public void add(E e) {
        int index = Arrays.binarySearch(list.getContainer(), 0, list.getLastIndex(), e);
        if(index < 0) {
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
}
