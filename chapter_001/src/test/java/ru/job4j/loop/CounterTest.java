package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author atrifonov
* @since 06.07.2017
* @version 1
*/
public class CounterTest {
	/**
	* Test add.
	*/
	@Test
	public void whenSumEvenNumbersFromOneToTenThenThirty() {
		Counter counter = new Counter();
		int result = counter.add(1, 10);
		int expected = 30;
		assertThat(result, is(expected));
	}

	/**
	* Test add.
	*/
	@Test
	public void whenSumEvenNumbersFromZeroToOneThenZero() {
		Counter counter = new Counter();
		int result = counter.add(0, 1);
		int expected = 0;
		assertThat(result, is(expected));
	}

	/**
	* Test add.
	*/
	@Test
	public void whenSumEvenNumbersFromMinusTenToTenThenZero() {
		Counter counter = new Counter();
		int result = counter.add(-10, 10);
		int expected = 0;
		assertThat(result, is(expected));
	}

	/**
	* Test add.
	*/
	@Test
	public void whenSumEvenNumbersFromMinusTenToZeroThenMinusThirty() {
		Counter counter = new Counter();
		int result = counter.add(-10, 0);
		int expected = -30;
		assertThat(result, is(expected));
	}

	/**
	* Test add.
	*/
	@Test
	public void whenSumEvenNumbersFromTenToOneThenZero() {
		Counter counter = new Counter();
		int result = counter.add(10, 1);
		int expected = 0;
		assertThat(result, is(expected));
	}
}