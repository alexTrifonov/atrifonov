package ru.job4j.taskList;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author atrifonov.
 * @since 23.08.2017.
 * @version 1.
 */
public class TestCheckCycle {
    /**
     * Test hasCycle.
     */
    @Test
    public void whenListHastCycleThenTrue() {
        Node<String> sentinel = new Node<>(null);
        Node<String> first = new Node<>("a");
        Node<String> second = new Node<>("b");
        Node<String> third = new Node<>("c");
        Node<String> fourth = new Node<>("d");

        sentinel.next = first;
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;

        assertThat(CheckCycle.hasCycle(sentinel), is(true));
    }

    /**
     * Test hasCycle.
     */
    @Test
    public void whenListHasNotCycleThenFalse() {
        Node<Integer> sentinel = new Node<>(null);
        Node<Integer> one = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> three = new Node<>(3);

        sentinel.next = one;
        one.next = two;
        two.next = three;

        assertThat(CheckCycle.hasCycle(sentinel), is(false));
    }

    /**
     * Test hasCycleTwo.
     */
    @Test
    public void whenListHastCycleThenHasCycleTwoReturnTrue() {
        Node<String> first = new Node<>("a");
        Node<String> second = new Node<>("b");
        Node<String> third = new Node<>("c");
        Node<String> fourth = new Node<>("d");

        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;

        assertThat(CheckCycle.hasCycleTwo(first), is(true));
    }

    /**
     * Test hasCycleTwo.
     */
    @Test
    public void whenListHasNotCycleThenHasCycleTwoReturnFalse() {
        Node<Integer> one = new Node<>(1);
        Node<Integer> two = new Node<>(2);
        Node<Integer> three = new Node<>(3);

        one.next = two;
        two.next = three;

        assertThat(CheckCycle.hasCycleTwo(one), is(false));
    }
}
