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
public class ArraySubstringTest {
	/**
	* Test contains.
	*/
	@Test
	public void whenStringHasSubstringThenTrue() {
		String origin = "Привет";
		String sub = "иве";
		ArraySubstring arraySubstring = new ArraySubstring();
		boolean result = arraySubstring.contains(origin, sub);
		boolean expected = true;
		assertThat(result, is(expected));
	}

	/**
	* Test contains.
	*/
	@Test
	public void whenStringHasNotSubstringThenFalse() {
		String origin = "Привет";
		String sub = "Вася";
		ArraySubstring arraySubstring = new ArraySubstring();
		boolean result = arraySubstring.contains(origin, sub);
		boolean expected = false;
		assertThat(result, is(expected));
	}

	/**
	* Test contains.
	*/
	@Test
	public void whenStringHasSubstringOneCharThenTrue() {
		String origin = "Привет";
		String sub = "и";
		ArraySubstring arraySubstring = new ArraySubstring();
		boolean result = arraySubstring.contains(origin, sub);
		boolean expected = true;
		assertThat(result, is(expected));
	}
}