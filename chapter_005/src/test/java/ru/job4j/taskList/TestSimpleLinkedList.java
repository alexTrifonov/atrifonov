package ru.job4j.taskList;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author atrifonov.
 * @since 27.08.2017.
 * @version 1.
 */
public class TestSimpleLinkedList {
    /**
     * Test hasNext, next and remove.
     */
    @Test
    public void whenRemoveLastItemThenListHaveMinusOneItem() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");

        Iterator<String> iterator = list.iterator();
        for (int i = 0; i < 6; i++) {
            iterator.next();
        }
        iterator.remove();

        iterator = list.iterator();
        String lastItem = null;

        while (iterator.hasNext()) {
            lastItem = iterator.next();
        }
        assertThat(lastItem, is("e"));
    }

    /**
     * Test remove.
     */
    @Test
    public void whenAddOneAndRemoveOneThenNoHasItem() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();

        iterator = list.iterator();
        boolean itHas = iterator.hasNext();
        assertThat(itHas, is(false));
    }

    /**
     * Test remove.
     */
    @Test
    public void whenAddThreeAndRemoveSecondThenHasTwoItem() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();

        iterator = list.iterator();
        String lastItem = null;

        while (iterator.hasNext()) {
            lastItem = iterator.next();
        }

        assertThat(lastItem, is("c"));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddFourItemThenIteratorGiveTheseFourItem() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        List<String> list1 = new ArrayList<>();
        Iterator<String> it = list.iterator();
        while(it.hasNext()) {
            list1.add(it.next());
        }
        ArrayList<String> expectedList = new ArrayList<>(Arrays.asList(new String[] {"a", "b", "c", "d"}));
        assertThat(list1, is(expectedList));
    }

    /**
     * Test next.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddThreeItemAndInvokeNextFourTimeThenGetNoSuchElementException() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    /**
     * Test remove in iterator.
     */
    @Test(expected = IllegalStateException.class)
    public void whenRemoveBeforeNextThenIllegalStateException() throws IllegalStateException {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("a");
        list.add("b");
        Iterator<String> iterator = list.iterator();
        iterator.remove();
    }

    /**
     * Test get.
     */
    @Test
    public void whenAddThreeItemAndGetSecondItemThenGetSecondItem() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        assertThat(list.get(1), is("b"));
    }

    /**
     * Test get.
     */
    @Test
    public void whenAddThreeItemAndGetIndexItemMinusOneThenGetNull() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("d");
        String a = null;
        assertThat(list.get(-1), is(a));
    }

    /**
     * Test get.
     */
    @Test
    public void whenAddThreeItemAndGetFourItemThenGetNull() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        String a = null;
        assertThat(list.get(3), is(a));
    }
}
