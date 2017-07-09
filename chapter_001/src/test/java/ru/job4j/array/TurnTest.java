package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
* Test.
*
* @author atrifonov.
* @since 09.07.2017.
* @version 1.
*/
public class TurnTest {
    /**
	* Test back.
	*/
	@Test
    public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
		int[] initialArray = new int[8];
		int[] expectedArray = new int[8];
		for (int i = 0; i != initialArray.length; i++) {
			initialArray[i] = i;
			expectedArray[initialArray.length - 1 - i] = i;
		}
		Turn turn = new Turn();
		int[] resultArray;
		resultArray = turn.back(initialArray);
		assertThat(resultArray, is(expectedArray));
    }

    /**
	* Test back.
	*/
	@Test
    public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
		int[] initialArray = new int[7];
		int[] expectedArray = new int[7];
		for (int i = 0; i != initialArray.length; i++) {
			initialArray[i] = i;
			expectedArray[initialArray.length - 1 - i] = i;
		}
		Turn turn = new Turn();
		int[] resultArray;
		resultArray = turn.back(initialArray);
		assertThat(resultArray, is(expectedArray));
    }

	/**
	* Test back.
	*/
	@Test
    public void whenTurnArrayWithOneElementThenTurnedThisArray() {
		int[] initialArray = new int[1];
		initialArray[0] = 2;
		int[] expectedArray = initialArray;
		Turn turn = new Turn();
		int[] resultArray;
		resultArray = turn.back(initialArray);
		assertThat(resultArray, is(expectedArray));
    }
}