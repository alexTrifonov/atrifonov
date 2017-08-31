package ru.job4j.taskList;

import org.junit.Test;
import ru.job4j.taskList.EmptySimpleQueueException;
import ru.job4j.taskList.SimpleQueue;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author atrifonov.
 * @since 28.08.2017.
 * @version 1.
 */
public class TestSimpleQueue {
    /**
     * Test enqueue and dequeue.
     */
    @Test
    public void whenEnqueueTwoItemThenDequeFirstItem() {
        SimpleQueue<String> queue = new SimpleQueue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        assertThat(queue.dequeue(), is("a"));
    }

    /**
     * Test enqueue and dequeue.
     */
    @Test(expected = EmptySimpleQueueException.class)
    public void whenDequeueInEmptyQueueThenEmptySimpleQueueException() {
        SimpleQueue<String> queue = new SimpleQueue<>();
        queue.enqueue("a");
        queue.dequeue();
        queue.dequeue();
    }
}
