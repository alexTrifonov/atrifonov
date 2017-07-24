package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 24.07.2017.
 * @version 1.
 */
public class ArrayFromTwoArraysTest {
    /**
     * Test arrayFromTwoSortedArrays.
     */
    @Test
    public void whenTwoSortedArrayThenOneBigSortedArray() {
        int[] a = {1, 3, 5, 7};
        int[] b = {2, 4, 6, 8};
        ArrayFromTwoArrays arrayFromTwoArrays = new ArrayFromTwoArrays();
        int[] resultArray = arrayFromTwoArrays.arrayFromTwoSortedArrays(a, b);
        assertThat(resultArray, is(new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
    }

    /**
     * Test arrayFromTwoSortedArrays.
     */
    @Test
    public void whenFirstSmallSortedArrayAndSecondBigSortedArrayThenOneBigSortedArray() {
        int[] a = {1, 3};
        int[] b = {6, 8};
        ArrayFromTwoArrays arrayFromTwoArrays = new ArrayFromTwoArrays();
        int[] resultArray = arrayFromTwoArrays.arrayFromTwoSortedArrays(a, b);
        assertThat(resultArray, is(new int[]{1, 3, 6,  8}));
    }

    /**
     * Test arrayFromTwoSortedArrays.
     */
    @Test
    public void whenTwoSameSortedArrayThenOneDuplicateSortedArray() {
        int[] a = {-1, 0, 1};
        int[] b = {-1, 0, 1};
        ArrayFromTwoArrays arrayFromTwoArrays = new ArrayFromTwoArrays();
        int[] resultArray = arrayFromTwoArrays.arrayFromTwoSortedArrays(a, b);
        assertThat(resultArray, is(new int[]{-1, -1, 0, 0, 1, 1}));
    }
}
