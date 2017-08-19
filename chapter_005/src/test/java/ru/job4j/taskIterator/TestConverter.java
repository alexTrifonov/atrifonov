package ru.job4j.taskIterator;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author atrifonov.
 * @since 16.08.2017.
 * @version 1.
 */
public class TestConverter {
    /**
     * Test convert.
     */
    @Test
    public void whenItHasTwoInnerIt() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Collections.singletonList(1).iterator(),
                Collections.singletonList(2).iterator()
        ).iterator();
        Iterator<Integer> convert = new Converter().convert(it);
        convert.next();
        int result = convert.next();
        assertThat(result, is(2));
    }

    /**
     * Test convert.
     */
    @Test
    public void whenItHasTwoInnerIteratorsWithTwoIntegerThenOneIteratorWithFourInteger() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> iterator = list.iterator();

        List<Integer> listTwo = new ArrayList<>();
        listTwo.add(3);
        listTwo.add(4);
        Iterator<Integer> iteratorTwo = listTwo.iterator();

        List<Iterator<Integer>> listIterator = new ArrayList<>();
        listIterator.add(iterator);
        listIterator.add(iteratorTwo);

        Iterator<Integer> it = new Converter().convert(listIterator.iterator());

        List<Integer> expectedList = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            expectedList.add(i);
        }

        List<Integer> resultList = new ArrayList<>();
        while (it.hasNext()) {
            resultList.add(it.next());
        }
        assertThat(resultList, is(expectedList));
    }
}
