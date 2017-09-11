package ru.job4j.taskTree;

/**
 * Interface  for simple tree.
 * @author atrifonov.
 * @since 11.09.2017.
 * @version 1.
 * @param <E> the type of stored items.
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Add child in parent.
     * Parent has or hasn't list of children.
     * @param parent the parent.
     * @param child the child.
     * @return true, if tree consist node for condition: node.value.compare(parent) == 0.
     */
    boolean add(E parent, E child);
}
