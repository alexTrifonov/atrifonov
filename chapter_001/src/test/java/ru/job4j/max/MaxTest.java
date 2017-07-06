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

	/**
	* Test max.
	*/
	@Test
	public void whenMaxSevenAndSevenThenSeven() {
		Max maxInstance = new Max();
		int result = maxInstance.max(7, 7);
		int expected = 7;
		assertThat(result, is(expected));
	}

	/**
	* Test max for three arguments.
	*/
	@Test
	public void whenMaxThreeMoreTwoMoreOneThenThree() {
		Max maxInstanceForThree = new Max();
		int result = maxInstanceForThree.max(1, 2, 3);
		int expected = 3;
		assertThat(result, is(expected));
	}

	/**
	* Test max for three arguments.
	*/
	@Test
	public void whenMaxMinusOneLessTwoMoreMinusTenThenTwo() {
		Max maxInstanceForThree = new Max();
		int result = maxInstanceForThree.max(-1, 2, -10);
		int expected = 2;
		assertThat(result, is(expected));
	}

	/**
	* Test max for three arguments.
	*/
	@Test
	public void whenMaxZeroEqualZeroEqualZeroThenZero() {
		Max maxInstanceForThree = new Max();
		int result = maxInstanceForThree.max(0, 0, 0);
		int expected = 0;
		assertThat(result, is(expected));
	}

	/**
	* Test max for three arguments.
	*/
	@Test
	public void whenMaxMinusTenMoreMinusTwentyLessMinusElevenThenMax() {
		Max maxInstanceForThree = new Max();
		int result = maxInstanceForThree.max(-10, -20, -11);
		int expected = -10;
		assertThat(result, is(expected));
	}

	/**
	* Test max for three arguments.
	*/
	@Test
	public void whenMaxOneMoreZeroLessOneThenMax() {
		Max maxInstanceForThree = new Max();
		int result = maxInstanceForThree.max(1, 0, 1);
		int expected = 1;
		assertThat(result, is(expected));
	}

	/**
	* Test max for three arguments.
	*/
	@Test
	public void whenMaxOneLessTwoMoreOneThenMax() {
		Max maxInstanceForThree = new Max();
		int result = maxInstanceForThree.max(1, 2, 1);
		int expected = 2;
		assertThat(result, is(expected));
	}
}