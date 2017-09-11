package ru.job4j.taskTree;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 11.09.2017.
 * @version 1.
 */
public class TestTree {
    /**
     * Test add.
     */
    @Test
    public void whenAddFourItemThenIteratorReturnFourItem() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        Iterator<Integer> iterator = tree.iterator();
        List<Integer> list = new ArrayList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        assertThat(list.size(), is(4));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddItemWithMistakenParenThenItemWillNotAdd() {
        Tree<Integer> tree = new Tree<>();
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(6, 7);
        int count = 0;
        for(Integer x : tree) {
            count++;
        }
        assertThat(count, is(5));
    }
}
