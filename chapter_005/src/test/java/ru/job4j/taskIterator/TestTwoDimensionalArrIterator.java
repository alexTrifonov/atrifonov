package ru.job4j.taskIterator;

import org.junit.Test;
import ru.job4j.taskIterator.TwoDimensionalArrIterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import java.util.LinkedList;
import java.util.List;

/**
 * Test
 *
 * @author atrifonov.
 * @since 16.08.2017.
 * @version 1.
 */
public class TestTwoDimensionalArrIterator {
    /**
     * Test
     */
    @Test
    public void whenArrayTwoOnTwoThenIteratorReturnForNumbers() {
        TwoDimensionalArrIterator it = new TwoDimensionalArrIterator(new int[][]{{1,2}, {3,4}});
        List<Integer> list = new LinkedList<>();
        while (it.hasNext()){
            list.add(it.next());
        }
        List<Integer> expectedList = new LinkedList<Integer>(Arrays.asList(new Integer[]{1,2,3,4}));
        assertThat(list, is(expectedList));
    }
}
