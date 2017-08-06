package ru.job4j.begin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 06.08.2017.
 * @version 1.
 */
public class TestConvertList {
    /**
     * Test toList.
     */
    @Test
    public void whenTwoDimensionArrayThenListWithAllArrayItems() {
        ConvertList convertList = new ConvertList();
        int[][] arrayInt = {{1, 2, 3}, {4, 5, 6}};
        List<Integer> resultList = convertList.toList(arrayInt);
        List<Integer> expectedList = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            expectedList.add(i+1);
        }
        assertThat(resultList, is(expectedList));
    }
    /**
     * Test toList.
     */
    @Test
    public void whenEmptyTwoDimensionArrayThenEmptyList() {
        ConvertList convertList = new ConvertList();
        int[][] arrayInt = new int[0][0];
        List<Integer> resultList = convertList.toList(arrayInt);
        List<Integer> expectedList = new ArrayList<>();
        assertThat(resultList, is(expectedList));
    }

    /**
     * Test toArray.
     */
    @Test
    public void whenHaveThreeRowAndListSevenItemThenArrayWithThreeRowAndThreeColumn() {
        ConvertList convertList = new ConvertList();
        List<Integer> list = new ArrayList<>(7);
        for(int i = 0; i < 7; i++) {
            list.add(i+1);
        }
        int[][] resultArray = convertList.toArray(list, 3);
        int[][] expectedArray = {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        assertThat(resultArray, is(expectedArray));
    }

    /**
     * Test toArray.
     */
    @Test
    public void whenHaveFourRowAndListNineItemThenArrayWithFourRowAndThreeColumn() {
        ConvertList convertList = new ConvertList();
        List<Integer> list = new ArrayList<>(9);
        for(int i = 0; i < 9; i++) {
            list.add(i+1);
        }
        int[][] resultArray = convertList.toArray(list, 4);
        int[][] expectedArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {0, 0, 0}};
        assertThat(resultArray, is(expectedArray));
    }

    /**
     * Test toArray.
     */
    @Test
    public void whenEmptyListThenArrayWithOnlyZeroItems() {
        ConvertList convertList = new ConvertList();
        List<Integer> list = new ArrayList<>();
        int[][] resultArray = convertList.toArray(list, 4);
        int[][] expectedArray = {{0}, {0}, {0}, {0}};
        assertThat(resultArray, is(expectedArray));
    }

    /**
     * Test toArray.
     */
    @Test
    public void whenHaveFourRowAndListEightItemThenArrayWithFourRowAndTwoColumn() {
        ConvertList convertList = new ConvertList();
        List<Integer> list = new ArrayList<>(9);
        for(int i = 0; i < 8; i++) {
            list.add(i+1);
        }
        int[][] resultArray = convertList.toArray(list, 4);
        int[][] expectedArray = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        assertThat(resultArray, is(expectedArray));
    }

    /**
     * Test convert.
     */
    @Test
    public void whenTwoArrayInListWithTwoAndFourItemThenOneListWithSixItem() {
        ConvertList convertList = new ConvertList();
        List<int[]> list = new ArrayList<>(2);
        int[] a = {1,2};
        int[] b = {3,4,5,6};
        list.add(a);
        list.add(b);
        List<Integer> resultList = convertList.convert(list);
        List<Integer> expectedList = new ArrayList<>(6);
        for(int i = 0; i < 6; i++) {
            expectedList.add(i + 1);
        }

        assertThat(resultList, is(expectedList));
    }

    /**
     * Test convert.
     */
    @Test
    public void whenThreeArrayInListWithTwoAndZeroAndFourItemThenOneListWithSixItem() {
        ConvertList convertList = new ConvertList();
        List<int[]> list = new ArrayList<>(2);
        int[] a = {1,2};
        int[] c = new int[0];
        int[] b = {3,4,5,6};
        list.add(a);
        list.add(c);
        list.add(b);
        List<Integer> resultList = convertList.convert(list);
        List<Integer> expectedList = new ArrayList<>(6);
        for(int i = 0; i < 6; i++) {
            expectedList.add(i + 1);
        }

        assertThat(resultList, is(expectedList));
    }

}
