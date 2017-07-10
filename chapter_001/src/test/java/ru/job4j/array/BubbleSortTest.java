package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author atrifonov
* @since 10.07.2017
* @version 1
*/
public class BubbleSortTest {
	/**
	* Test sort.
	*/
	@Test
	public void whenSortArrayWithTenElementsThenSortedArray() {
		int[] array = {1, 5, 4, 2, 3, 1, 7, 8, 0, 5};
		BubbleSort bubbleSort = new BubbleSort();
		int[] resultArray = bubbleSort.sort(array);
		int[] expectedArray = {0, 1, 1, 2, 3, 4, 5, 5, 7, 8};
		assertThat(resultArray, is(expectedArray));
	}
}