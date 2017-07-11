package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author atrifonov.
* @since 11.07.2017.
* @version 1.
*/
public class RotateArrayTest {
	/**
	* Test back.
	*/
	@Test
	public void whenRotateTwoRowTwoColArrayThenRotateArray() {
		int[][] initialArray = {{1, 2}, {4, 3}};
		int[][] expectedArray = {{4, 1}, {3, 2}};
		RotateArray rotateArray = new RotateArray();
		int[][] resultArray = rotateArray.rotate(initialArray);
		assertThat(resultArray, is(expectedArray));
	}

	/**
	* Test back.
	*/
	@Test
	public void whenRotateThreeRowThreeColArrayThenRotateArray() {
		int[][] initialArray = {{1, 2, 3}, {8, 9, 4}, {7, 6, 5}};
		int[][] expectedArray = {{7, 8, 1}, {6, 9, 2}, {5, 4, 3}};
		RotateArray rotateArray = new RotateArray();
		int[][] resultArray = rotateArray.rotate(initialArray);
		assertThat(resultArray, is(expectedArray));
	}
}