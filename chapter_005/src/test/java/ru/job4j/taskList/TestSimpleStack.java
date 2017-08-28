package ru.job4j.taskList;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author atrifonov.
 * @since 28.08.2017.
 * @version 1.
 */
public class TestSimpleStack {
    /**
     * Test push and poll.
     */
    @Test
    public void whenPushThreeItemThenPopLastItem() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        assertThat(stack.pop(), is("c"));
    }

    /**
     * Test pop and EmptySmStackException.
     */
    @Test(expected = EmptySmStackException.class)
    public void whenPopInEmptyThenEmptySmStackException() {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("a");
        stack.push("b");
        stack.pop();
        stack.pop();
        stack.pop();
    }
}
