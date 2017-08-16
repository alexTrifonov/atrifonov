package ru.job4j.taskIterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author atrifonov.
 * @since 16.08.2017.
 * @version 1.
 */
public class TestPrimeIt {
    /**
     * Test.
     */
    @Test
    public void whenArrayHavePrimeNumbersThatIteratorReturnOnlyPrimeNumbers() {
        PrimeIt iterator = new PrimeIt(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17});
        List<Integer> list = new LinkedList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        List<Integer> expectedList = new LinkedList<>(Arrays.asList(new Integer[]{2,3,5,7,11,13,17}));
        assertThat(list, is(expectedList));
    }
}
