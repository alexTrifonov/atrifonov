package ru.job4j.taskTree;

import java.util.List;

/**
 * Class simple tree with check on binary.
 * @author atrifonov.
 * @since 11.09.2017.
 * @version 1.
 * @param <E> the type of stored items.
 */
public class BTree<E extends Comparable<E>> extends Tree<E> {
    /**
     * Check tree on binary.
     * @return true if tree is binary.
     */
    public boolean isBinary() {
        boolean isBinary = false;
        if(getRootNode() != null) {
            isBinary = binary(getRootNode().children);
        }
        return isBinary;
    }

    private boolean binary(List<Node<E>> list) {
        boolean binary = list.size() <=2;
        if(!binary) {
            return false;
        }
        for(Node<E> eNode : list) {
            binary = binary && binary(eNode.children);
        }
        return binary;
    }
}
