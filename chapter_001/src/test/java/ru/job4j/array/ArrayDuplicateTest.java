package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author atrifonov.
* @since 12.07.2017.
* @version 1.
*/
public class ArrayDuplicateTest {
	/**
	* Test remove.
	*/
	@Test
	public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
		 String[] initialArray = {"Мир", "Труд", "Мир", "Май", "Труд"};
		 ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
		 String[] resultArray = arrayDuplicate.remove(initialArray);
		 String[] expectedArray = {"Мир", "Труд", "Май"};
		 assertThat(resultArray, is(expectedArray));
	}
}