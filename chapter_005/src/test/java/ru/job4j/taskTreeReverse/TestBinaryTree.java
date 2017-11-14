package ru.job4j.taskTreeReverse;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author atrifonov.
 * @version 1.
 * @since 14.11.2017.
 */
public class TestBinaryTree {
    /**
     * Test for first and second manner reverse children.
     */
    @Test
    public void WhenReverseTwoTimeThenGetSameTree() {
        BinaryTree<Integer> bTree = new BinaryTree<>();
        bTree.addChild(1, 2, 3);
        bTree.addChild(2, 4, 5);
        bTree.addChild(3, 6, 7);
        bTree.addChild(6, 8, 9);
        String bTreeStr = bTree.toString();
        bTree.reverseLeftRight();
        String bTreeStrFirstReverse = bTree.toString();
        bTree.reverseLeftRightAnother(bTree.getRootNode());
        String bTreeStrSecondReverse = bTree.toString();
        boolean notEqual = !bTreeStr.equals(bTreeStrFirstReverse);
        boolean equal = bTreeStr.equals(bTreeStrSecondReverse);
        System.out.println(bTreeStr);
        System.out.println();
        System.out.println(bTreeStrFirstReverse);
        System.out.println(bTreeStrSecondReverse);
        assertThat(notEqual && equal, is(true));
    }
}
