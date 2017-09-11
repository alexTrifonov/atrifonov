package ru.job4j.taskTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for simple tree.
 * @author atrifonov.
 * @since 11.09.2017.
 * @version 1.
 * @param <E> the type of stored items.
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * The root node.
     */
    private Node<E> rootNode;

    /**
     * Class for node.
     * @param <E> the type of stored item.
     */
    class Node<E> {
        /**
         * List of children node.
         */
        List<Node<E>> children;
        /**
         * Stored value of node.
         */
        E value;

        /**
         * Construct node with specified value.
         * @param value the value for store.
         */
        Node(E value) {
            this.value = value;
            children = new LinkedList<>();
        }
    }

    @Override
    public boolean add(E parent, E child) {
        boolean successfully = false;

        if(parent != null && child != null && parent.equals(child)) {
            successfully = false;
        } else if(rootNode == null) {
            rootNode = new Node<>(parent);
            rootNode.children.add(new Node<>(child));
            successfully = true;
        } else if(!hasDuplicate(child)) {
            Node<E> nodeParent = nodeParent(parent);
            if(nodeParent != null) {
                nodeParent.children.add(new Node<>(child));
                successfully = true;
            }
        }

        return successfully;
    }

    @Override
    public Iterator<E> iterator() {
        List<E> listValue = new LinkedList<>();
        if(rootNode != null) {
            listValue = itemsList(rootNode);
        }
        return  listValue.iterator();
    }

    /**
     * Create list of all nodes in tree from assigned node.
     * @param node the first or root node in tree.
     * @return list of nodes.
     */
    private List<Node<E>> generateList(Node<E> node){
        LinkedList<Node<E>> list = new LinkedList<>();
        list.add(node);
        for(Node<E> x : node.children) {
            list.addAll(generateList(x));
        }
        return list;
    }

    /**
     * Create list of all items assigned from node.
     * @param node the first or root node in tree.
     * @return list of items.
     */
    private List<E> itemsList(Node<E> node) {
        LinkedList<E> list = new LinkedList<>();
        list.add(node.value);
        for(Node<E> x : node.children){
            list.addAll(itemsList(x));
        }
        return list;
    }

    /**
     * Find node parent in tree.
     * @param parent Item for compare.
     * @return node parent or null if tree hasn't node with parent.
     */
    private Node<E> nodeParent(E parent){
        Node<E> nodeParent = null;
        if(rootNode != null) {
            List<Node<E>> nodeList = generateList(rootNode);
            for(Node<E> node : nodeList) {
                if(node.value.compareTo(parent) == 0) {
                    nodeParent = node;
                }
            }
        }
        return nodeParent;
    }

    /**
     * Check on duplicate.
     * @param child item for check.
     * @return false if tree hasn't child.
     */
    private boolean hasDuplicate(E child) {
        boolean has = false;
        for(E x : this) {
            if(x.compareTo(child) == 0 ) {
                has = true;
                break;
            }
        }
        return has;
    }

    public Node<E> getRootNode() {
        return rootNode;
    }
}

