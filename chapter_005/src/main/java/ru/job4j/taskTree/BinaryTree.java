package ru.job4j.taskTree;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for simple binary tree.
 * @author atrifonov.
 * @since 12.09.2017.
 * @version 1.
 * @param <E> the type of stored items.
 */
public class BinaryTree<E extends Comparable<E>> {
    /**
     * The root node.
     */
    private NodeB<E> rootNode;

    /**
     * Add item in tree.
     * @param e item for adding.
     * @return true, if tree hasn't this item and tree successfully added item.
     */
    public boolean add(E e) {
        boolean successfully = false;
        if(rootNode == null) {
            rootNode = new NodeB<>(e);
            successfully = true;
        } else {
            addBinary(e, null);
        }

        return successfully;
    }

    /**
     * Find vacant place and add item to left child node, if item less node value,
     * or to right child node, if item more node value.
     * @param e item for adding.
     * @param nodeB node for start.
     * @return true, if successfully added.
     */
    private boolean addBinary(E e, NodeB<E> nodeB) {
        boolean addE = false;
        if(nodeB == null) {
            nodeB = rootNode;
        }
        if(e.compareTo(nodeB.value) < 0) {
            if(nodeB.leftChild == null) {
                nodeB.leftChild = new NodeB<>(e);
                addE = true;
            } else {
                nodeB = nodeB.leftChild;
                addBinary(e, nodeB);
            }
        } else if(e.compareTo(nodeB.value) > 0) {
            if(nodeB.rightChild == null) {
                nodeB.rightChild = new NodeB<>(e);
                addE = true;
            } else {
                nodeB = nodeB.rightChild;
                addBinary(e, nodeB);
            }
        } else {
            addE = false;
        }
        return addE;
    }



    private class NodeB<E>{
        /**
         * Stored value of node.
         */
        private E value;
        /**
         * Reference on left child this node.
         */
        private NodeB<E> leftChild;
        /**
         * Reference on right child this node.
         */
        private NodeB<E> rightChild;
        /**
         * Construct node with specified value.
         * @param value the value for store.
         */
        NodeB(E value) {
            this.value = value;
        }
    }

    /**
     * Create list of all items in tree.
     * @return list.
     */
    public List<E> getValues() {
        return generateValue(rootNode);
    }

    /**
     * Create list of all items in tree after assigned node.
     * @param nodeB assigned node.
     * @return list of items.
     */
    private List<E> generateValue(NodeB<E> nodeB) {
        List<E> list = new LinkedList<>();
        if(nodeB != null) {
            list.add(nodeB.value);
            list.addAll(generateValue(nodeB.leftChild));
            list.addAll(generateValue(nodeB.rightChild));
        }
        return list;
    }


}
