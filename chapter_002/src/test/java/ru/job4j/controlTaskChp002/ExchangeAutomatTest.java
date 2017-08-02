package ru.job4j.controlTaskChp002;

import org.junit.Test;
import ru.job4j.сontrolTaskChpt002.ExchangeAutomat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 02.08.2017.
 * @version 1.
 */
public class ExchangeAutomatTest {
    /**
     * Test exchangeOneManner.
     */
    @Test
    public void whenChangeFortyThenHaveEightFiveCoin() {
        ExchangeAutomat exchangeAutomat = new ExchangeAutomat();
        List<Integer> resultList = exchangeAutomat.exchangeOneManner(40);
        List<Integer> expectedList = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            expectedList.add(5);
        }
        assertThat(resultList, is(expectedList));
    }

    /**
     * Test exchangeAllManner.
     */
    @Test
    public void whenChangeThirtyThenSixteenListExchange() {
        ExchangeAutomat exchangeAutomat = new ExchangeAutomat();
        List<List<Integer>> resultList = exchangeAutomat.exchangeAllManners(30);
        List<List<Integer>> expectedList = new ArrayList<>();
        Integer[] a1 = {5, 5, 5, 5, 5, 1, 1, 1, 1, 1};
        expectedList.add(new ArrayList<>(Arrays.asList(a1)));

        Integer[] a2 = {5, 5, 5, 5 };
        expectedList.add(new ArrayList<>(Arrays.asList(a2)));
        for(int i = 0; i < 10; i++) {
            expectedList.get(1).add(1);
        }

        Integer[] a3 = {5, 5, 5};
        expectedList.add(new ArrayList<>(Arrays.asList(a3)));
        for(int i = 0; i < 15; i++) {
            expectedList.get(2).add(1);
        }

        Integer[] a4 = {5, 5};
        expectedList.add(new ArrayList<>(Arrays.asList(a4)));
        for(int i = 0; i < 20; i++) {
            expectedList.get(3).add(1);
        }

        Integer[] a5 = {5};
        expectedList.add(new ArrayList<>(Arrays.asList(a5)));
        for(int i = 0; i < 25; i++) {
            expectedList.get(4).add(1);
        }

        Integer[] a6 = {10, 5, 5, 5, 5};
        expectedList.add(new ArrayList<>(Arrays.asList(a6)));

        Integer[] a7 = {10, 5, 5, 5, 1, 1, 1, 1, 1};
        expectedList.add(new ArrayList<>(Arrays.asList(a7)));

        Integer[] a8 = {10, 5, 5};
        expectedList.add(new ArrayList<>(Arrays.asList(a8)));
        for(int i = 0; i < 10; i++) {
            expectedList.get(7).add(1);
        }

        Integer[] a9 = {10, 5};
        expectedList.add(new ArrayList<>(Arrays.asList(a9)));
        for(int i = 0; i < 15; i++) {
            expectedList.get(8).add(1);
        }

        Integer[] a10 = {10};
        expectedList.add(new ArrayList<>(Arrays.asList(a10)));
        for(int i = 0; i < 20; i++) {
            expectedList.get(9).add(1);
        }

        Integer[] a11 = {10, 10, 5, 5};
        expectedList.add(new ArrayList<>(Arrays.asList(a11)));

        Integer[] a12 = {10, 10, 5, 1, 1, 1, 1, 1};
        expectedList.add(new ArrayList<>(Arrays.asList(a12)));

        Integer[] a13 = {10, 10};
        expectedList.add(new ArrayList<>(Arrays.asList(a13)));
        for(int i = 0; i < 10; i++) {
            expectedList.get(12).add(1);
        }

        Integer[] a14 = {10, 10, 10};
        expectedList.add(new ArrayList<>(Arrays.asList(a14)));

        Integer[] a15 = {5, 5, 5, 5, 5, 5};
        expectedList.add(new ArrayList<>(Arrays.asList(a15)));


        expectedList.add(new ArrayList<>());
        for(int i = 0; i < 30; i++) {
            expectedList.get(15).add(1);
        }
        assertThat(resultList, is(expectedList));
    }

    /**
     * Test exchangeAllManner.
     */
    @Test
    public void whenChangeTenThenFourListExchange() {
        ExchangeAutomat exchangeAutomat = new ExchangeAutomat();
        List<List<Integer>> resultList = exchangeAutomat.exchangeAllManners(10);
        List<List<Integer>> expectedList = new ArrayList<>();

        Integer[] a1 = {5};
        expectedList.add(new ArrayList<>(Arrays.asList(a1)));
        for(int i = 0; i < 5; i++) {
            expectedList.get(0).add(1);
        }

        List<Integer> list = new ArrayList<>();
        list.add(10);
        expectedList.add(list);

        expectedList.add(new ArrayList<>());
        for(int i = 0; i < 2; i++) {
            expectedList.get(2).add(5);
        }

        expectedList.add(new ArrayList<>());
        for(int i = 0; i < 10; i++) {
            expectedList.get(3).add(1);
        }

        assertThat(resultList, is(expectedList));
    }
}
