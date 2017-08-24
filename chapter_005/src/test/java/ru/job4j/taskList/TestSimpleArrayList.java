package ru.job4j.taskList;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author atrifonov.
 * @since 24.08.2017.
 * @version 1.
 */
public class TestSimpleArrayList {
    /**
     * Test add and get.
     */
    @Test
    public void whenAddThreeItemThenSeconItemEqualsGetIndexOne() {
        SimpleArrayList<String> list = new SimpleArrayList<>(5);
        list.add("a");
        list.add("b");
        list.add("c");
        assertThat(list.get(1), is("b"));
    }

    /**
     * Test add and get.
     */
    @Test
    public void whenAddThreeItemThenGettingThreeItemEqualsNull() {
        SimpleArrayList<String> list = new SimpleArrayList<>(5);
        list.add("a");
        list.add("b");
        list.add("c");
        String a = null;
        assertThat(list.get(3), is(a));
    }

    /**
     * Test iterator.
     */
    @Test
    public void whenAddListFiveItemIteratorExactlyReturnItems() {
        SimpleArrayList<String> list = new SimpleArrayList<>(4);
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        Iterator<String> iterator = list.iterator();
        String a = null;
        String c = iterator.next();
        while(iterator.hasNext()) {
            a = iterator.next();
        }
        boolean lastNull = c.equals("a") && a.equals("e");
        assertThat(lastNull, is(true));
    }

    /**
     * Test remove in iterator.
     */
    @Test(expected = IllegalStateException.class)
    public void whenRemoveTwoTimeThenIllegalStateException() throws IllegalStateException {
        SimpleArrayList<String> list = new SimpleArrayList<>(4);
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        iterator.remove();
    }

    /**
     * Test remove in iterator.
     */
    @Test(expected = IllegalStateException.class)
    public void whenRemoveBeforeNextThenIllegalStateException() throws IllegalStateException {
        SimpleArrayList<String> list = new SimpleArrayList<>(4);
        list.add("a");
        list.add("b");
        Iterator<String> iterator = list.iterator();
        iterator.remove();
    }

    /**
     * Test next in iterator.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenIteratorInvokeThreeTimeInListhWithTwoItemThenNoSuchElementException() {
        SimpleArrayList<String> list = new SimpleArrayList<>(4);
        list.add("a");
        list.add("b");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
    }
}
