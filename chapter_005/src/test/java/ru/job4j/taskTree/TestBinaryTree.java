package ru.job4j.taskTree;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 12.09.2017.
 * @version 1.
 */
public class TestBinaryTree {
    /**
     * Test add, getValues.
     */
    @Test
    public void whenAddFiveItemThenGetFiveItem(){
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.add(10);
        tree.add(7);
        tree.add(11);
        tree.add(5);
        tree.add(9);
        tree.add(14);
        tree.add(12);
        tree.add(15);
        tree.add(5);
        tree.add(9);
        List<Integer> list = tree.getValues();
        Collections.sort(list);
        Integer[] ar  = {5, 7, 9, 10, 11, 12, 14, 15};
        List<Integer> listExpected = new LinkedList<>(Arrays.asList(ar));
        assertThat(list, is(listExpected));
    }
}
