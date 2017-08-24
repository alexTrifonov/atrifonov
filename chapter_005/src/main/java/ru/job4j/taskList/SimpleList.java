package ru.job4j.taskList;

/**
 * Interface for simple lists.
 * @author atrifonov.
 * @since 24.08.2017.
 * @version 1.
 * @param <E> the type of elements in this list.
 */
public interface SimpleList<E> extends Iterable<E> {
    /**
     * Add object to list.
     * @param e object for adding.
     */
    void add(E e);

    /**
     * Get object from lis t.
     * @param index index of object.
     * @return if index less count objects in list return object, else return null.
     */
    E get(int index);
}