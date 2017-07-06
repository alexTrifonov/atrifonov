package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author atrifonov.
* @since 06.07.2017
* @version 1
*/
public class FactorialTest {
    /**
	* Test calc.
	*/
	@Test
    public void whenCalculateFactorialForFiveThenOneHundreedTwenty() {
        Factorial factorial = new Factorial();
		int result = factorial.calc(5);
		int expected = 120;
		assertThat(result, is(expected));
    }

    /**
	* Test calc.
	*/
	@Test
    public void whenCalculateFactorialForZeroThenOne() {
        Factorial factorial = new Factorial();
		int result = factorial.calc(0);
		int expected = 1;
		assertThat(result, is(expected));
    }

	/**
	* Test calc.
	*/
	@Test
    public void whenCalculateFactorialForNegativeNumberThenZero() {
        Factorial factorial = new Factorial();
		int result = factorial.calc(-10);
		int expected = 0;
		assertThat(result, is(expected));
    }
}