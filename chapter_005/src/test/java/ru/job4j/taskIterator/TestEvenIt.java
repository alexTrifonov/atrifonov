package ru.job4j.taskIterator;

import org.junit.Test;
import ru.job4j.taskIterator.EvenIt;

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
public class TestEvenIt {
    /**
     * Test.
     */
    @Test
    public void whenArrayHaveEvenNumbersThatIteratorReturnOnlyEvenNumbers() {
        EvenIt iterator = new EvenIt(new int[]{1,2,3,4,5,6,7,1,3,4,6,8});
        List<Integer> list = new LinkedList<>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        List<Integer> expectedList = new LinkedList<>(Arrays.asList(new Integer[]{2,4,6,4,6,8}));
        assertThat(list, is(expectedList));
    }
}
