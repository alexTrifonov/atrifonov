package ru.job4j.calculator;

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
public class CalculatorTest {
	/**
	* Test add.
	*/
	@Test
	public void whenAddOnePlusOneThenTwo() {
		Calculator calc = new Calculator();
		calc.add(1D, 1D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}

	/**
	* Test subtruct.
	*/
	@Test
	public void whenSubtructOneMinusOneThenZero() {
		Calculator calc = new Calculator();
		calc.subtruct(1D, 1D);
		double result = calc.getResult();
		double expected = 0D;
		assertThat(result, is(expected));
	}

	/**
	* Test div.
	*/
	@Test
	public void whenDivFourDivideTwoThenTwo() {
		Calculator calc = new Calculator();
		calc.div(4D, 2D);
		double result = calc.getResult();
		double expected = 2D;
		assertThat(result, is(expected));
	}

	/**
	* Test multiple.
	*/
	@Test
	public void whenMultipleTwoMultiplyTwoThenFour() {
		Calculator calc = new Calculator();
		calc.multiple(2D, 2D);
		double result = calc.getResult();
		double expected = 4D;
		assertThat(result, is(expected));
	}
}