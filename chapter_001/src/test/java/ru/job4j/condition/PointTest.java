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

	/**
	* Test is.
	*/
	@Test
	public void whenIsPointXTwoAndYSevenAndAThreeAndBOneThenTrue() {
		Point point = new Point(2, 7);
		boolean result = point.is(3, 1);
		boolean expected = true;
		assertThat(result, is(expected));
	}

	/**
	* Test is.
	*/
	@Test
	public void whenIsPointXMinus2AndYTwoAndAMinusTwoAndBMinusTwoThenTrue() {
		Point point = new Point(-2, 2);
		boolean result = point.is(-2, -2);
		boolean expected = true;
		assertThat(result, is(expected));
	}

	/**
	* Test is.
	*/
	@Test
	public void whenIsPointXRandomAndYZeroAndAZeroAndBZeroThenTrue() {
		int x = (new Double(Math.random() * 1000)).intValue();
		Point point = new Point(x, 0);
		boolean result = point.is(0, 0);
		boolean expected = true;
		assertThat(result, is(expected));
	}
}