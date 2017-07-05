package ru.job4j.max;

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
public class MaxTest {
	/**
	* Test max.
	*/
	@Test
	public void whenMaxTwoMoreOneThenTwo() {
		Max maxInstance = new Max();
		int result = maxInstance.max(2, 1);
		int expected = 2;
		assertThat(result, is(expected));
	}

	/**
	* Test max.
	*/
	@Test
	public void whenMaxOneMoreZeroThenOne() {
		Max maxInstance = new Max();
		int result = maxInstance.max(0, 1);
		int expected = 1;
		assertThat(result, is(expected));
	}

	/**
	* Test max.
	*/
	@Test
	public void whenMaxMinusOneMoreMinusTwoThenMinusOne() {
		Max maxInstance = new Max();
		int result = maxInstance.max(-1, -2);
		int expected = -1;
		assertThat(result, is(expected));
	}
}