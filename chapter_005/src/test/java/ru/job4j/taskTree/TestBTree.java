package ru.job4j.taskTree;


import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test
 *
 * @author atrifonov.
 * @since 11.09.2017.
 * @version 1.
 */
public class TestBTree {

    /**
     * Test isBinary.
     */
    @Test
    public void whenOneNodeHasThreeChildrenThenIsBinaryFalse() {
        BTree<Integer> bTree = new BTree<>();
        bTree.add(1,2);
        bTree.add(1,3);
        bTree.add(1,4);
        assertThat(bTree.isBinary(), is(false));
    }

    /**
     * Test isBinary.
     */
    @Test
    public void whenSecondNodeHasThreeChildrenThenIsBinaryFalse() {
        BTree<Integer> bTree = new BTree<>();
        bTree.add(1,2);
        bTree.add(1,3);


        bTree.add(2, 5);
        bTree.add(2, 6);
        bTree.add(2, 7);
        assertThat(bTree.isBinary(), is(false));
    }

    /**
     * Test isBinary.
     */
    @Test
    public void whenThreeLevelNodeHasFourChildrenThenIsBinaryFalse() {
        BTree<Integer> bTree = new BTree<>();
        bTree.add(1,2);
        bTree.add(1,3);

        bTree.add(2,5);

        bTree.add(2, 6);


        bTree.add(6, 7);
        bTree.add(6, 8);
        bTree.add(6, 9);
        bTree.add(6, 10);
        assertThat(bTree.isBinary(), is(false));
    }

    /**
     * Test isBinary.
     */
    @Test
    public void whenTreeIsBinaryThenTrue(){
        BTree<Integer> bTree = new BTree<>();
        bTree.add(1,2);
        bTree.add(1,3);

        bTree.add(2,5);
        bTree.add(2, 6);

        bTree.add(3, 4);
        bTree.add(3, 7);

        bTree.add(5, 8);
        bTree.add(5, 9);

        bTree.add(4, 10);
        bTree.add(4, 11);

        assertThat(bTree.isBinary(), is(true));
    }
}
