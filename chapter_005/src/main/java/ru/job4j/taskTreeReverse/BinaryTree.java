package ru.job4j.taskTreeReverse;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for binary tree. All node of tree whether has two children or hasn't children.
 * @author atrifonov.
 * @version 1.
 * @since 14.11.2017.
 * @param <E> Type of items.
 */
public class BinaryTree<E extends Comparable<E>> {
    /**
     * The root node of tree.
     */
    private Node<E> rootNode;

    /**
     * Inner class for node of tree. Node has value and two children.
     * @param <E> Type of item.
     */
    private class Node<E> {
        /**
         * Value of item in this node.
         */
        E value;
        /**
         * Left child of node.
         */
        Node<E> leftChild;
        /**
         * Right child of node.
         */
        Node<E> rightChild;
        /**
         * List for store of children.
         */
        List<Node<E>> children = new ArrayList<>();

        /**
         * Construct Node with item value.
         * @param value item value.
         */
        Node(E value) {
            this.value = value;
        }
    }

    /**
     * Setting value of root node.
     * @param rootValue new value of root node.
     */
    public void setRootNode(E rootValue) {
        Node<E> newRootNode = new Node<>(rootValue);
        this.rootNode = newRootNode;
    }

    /**
     * Add left child and right child to parent node.
     * @param parent value of parent node.
     * @param left value of left child node.
     * @param right value of right child node.
     * @return true if left and right node was added.
     */
    public boolean addChild(E parent, E left, E right) {
        boolean succef = false;
        Node<E> parentNode = null;
        if(parent != null && left != null && right != null) {
            if(this.rootNode == null) {
                setRootNode(parent);
                this.rootNode.leftChild = new Node<>(left);
                this.rootNode.rightChild = new Node<>(right);
                this.rootNode.children.add(this.rootNode.leftChild);
                this.rootNode.children.add(this.rootNode.rightChild);
                succef = true;
            } else if((parentNode = parentNode(parent)) != null && parentNode.children.isEmpty()) {
                parentNode.leftChild = new Node<>(left);
                parentNode.rightChild = new Node<>(right);
                parentNode.children.add(parentNode.leftChild);
                parentNode.children.add(parentNode.rightChild);
                succef = true;
            }
        }

        return succef;
    }

    /**
     * Getting the root node of tree.
     * @return root node of tree.
     */
    public Node<E> getRootNode() {
        return this.rootNode;
    }

    /**
     * Find of node with this value of parent.
     * @param parent value of required node.
     * @return node with value that is equal parent.
     */
    private Node<E> parentNode(E parent) {
        Node<E> parentNode = null;

        List<Node<E>> nodeList = generateList(rootNode);
        for(Node<E> node : nodeList) {
            if(node.value.compareTo(parent) == 0) {
                parentNode = node;
            }
        }

        return parentNode;
    }

    /**
     * Create list with all nodes of tree after assigned node.
     * @param node start node.
     * @return list of nodes after assigned node.
     */
    private List<Node<E>> generateList(Node<E> node){
        LinkedList<Node<E>> list = new LinkedList<>();
        if (node != null) {
            list.add(node);
            for(Node<E> x : node.children) {
                list.addAll(generateList(x));
            }
        }

        return list;
    }

    /**
     * First manner of reverse left and right children in all nodes of tree.
     */
    public void reverseLeftRight() {
        if(this.rootNode != null) {
            List<Node<E>> listNodes = generateList(this.rootNode);
            for(Node<E> x : listNodes) {
                if(!x.children.isEmpty()){
                    E temp = x.leftChild.value;
                    x.leftChild.value = x.rightChild.value;
                    x.rightChild.value = temp;
                }
            }
        }

    }

    /**
     * Second manner of reverse left and right children in all nodes of tree.
     * @param startNode start node.
     */
    public void reverseLeftRightAnother(Node<E> startNode) {
        if(startNode != null && !startNode.children.isEmpty()) {
            E temp = startNode.leftChild.value;
            startNode.leftChild.value = startNode.rightChild.value;
            startNode.rightChild.value = temp;
            reverseLeftRightAnother(startNode.leftChild);
            reverseLeftRightAnother(startNode.rightChild);
        }
    }

    @Override
    public String toString() {
        String bTree = "Tree is empty.";
        if(this.rootNode != null) {
            StringBuilder sb = new StringBuilder();
            for (Node<E> x : generateList(this.rootNode)) {
                sb.append(x.value.toString());
                sb.append(" ");
                if(!x.children.isEmpty()){
                    sb.append(": ");
                    sb.append("leftChild - ");
                    sb.append(x.leftChild.value).append(",");
                    sb.append("rightChild - ");
                    sb.append(x.rightChild.value).append(",");
                }
                sb.append("\r\n");
            }
            bTree = sb.toString();
        }
        return bTree;
    }
}