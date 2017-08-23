package ru.job4j.taskGeneric;

/**
 * Class for model store.
 * @author atrifonov.
 * @since 23.08.2017.
 * @version 1.
 */
public interface Store<T extends Base> {
    void add(T t);

    void update(T newT);

    T delete(T t);
}

