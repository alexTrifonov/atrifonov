package ru.job4j.taskGeneric;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author atrifonov.
 * @since 20.08.2017.
 * @version 1.
 */
public class TestSimpleArray {
    /**
     * Test add and get
     */
    @Test
    public void whenAddTwoItemThenGetItemOnPositionOne() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("a");
        simpleArray.add("b");
        assertThat(simpleArray.get(1), is("b"));
    }

    /**
     * Test update.
     */
    @Test
    public void whenAddThreeItemAndUpdateSecondItemThenGetSecondItem() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("a");
        simpleArray.add("b");
        simpleArray.add("c");
        simpleArray.update("d", 1);
        assertThat(simpleArray.get(1), is("d"));
    }
    /**
     * Test add and get
     */
    @Test
    public void whenAddTwoItemAndTryGetThirdItemThenWillGetNull() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("a");
        simpleArray.add("b");
        String st = null;
        assertThat(simpleArray.get(2), is(st));
    }

    /**
     * Test delete and get
     */
    @Test
    public void whenAddFiveItemAndDeleteThirdItemThenWillGetAnotherThirdItem() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("a");
        simpleArray.add("b");
        simpleArray.add("c");
        simpleArray.add("d");
        simpleArray.add("e");

        String deletedString = simpleArray.delete(2);
        boolean deleteAndGet = (deletedString.equals("c")) && (simpleArray.get(2).equals("d"));
        assertThat(deleteAndGet, is(true));
    }

    /**
     * Test size.
     */
    @Test
    public void whenCreateSimpleArrayWithFiveItemAndAddTenItemThenSizeIzTen() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        for(int i = 0; i < 10; i++) {
            simpleArray.add("" + i);
        }
        assertThat(simpleArray.size(), is(10));
    }

    /**
     * Test delete.
     */
    @Test
    public void whenDeleteFirstAndLastItemThenFirstItemAndLastItemWillBeAnother() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("a");
        simpleArray.add("b");
        simpleArray.add("c");
        simpleArray.add("d");
        simpleArray.add("e");

        simpleArray.delete(0);
        simpleArray.delete(3);

        boolean firstAndLast = (simpleArray.get(0).equals("b")) && (simpleArray.get(2).equals("d"));

        assertThat(firstAndLast, is(true));
    }
}
