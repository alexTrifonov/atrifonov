package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author atrifonov (mailto:a.trifonov7@yandex.ru)
* @version 1
* @since 04.07.2017
*/
public class PointTest {
	/**
	* Test is.
	*/
	@Test
	public void whenIsPointXOneAndYTwoAndAOneAndBOneThenTrue() {
		Point point = new Point(1, 2);
		boolean result = point.is(1, 1);
		boolean expected = true;
		assertThat(result, is(expected));
	}
}