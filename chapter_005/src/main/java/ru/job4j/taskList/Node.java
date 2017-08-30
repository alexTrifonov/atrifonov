package ru.job4j.taskList;

/**
 * Class for node for linked list.
 * @author atrifonov.
 * @since 30.08.2017.
 * @version 1
 * @param <T> the type of value in node.
 */
public class Node<T> {
    /**
     * Value of node.
     */
    private T value;
    /**
     * Reference on next item in linked list.
     */
    Node<T> next;

    /**
     * Construct node with value.
     * @param value value of node.
     */
    public Node(T value) {
        this.value = value;
    }
}
