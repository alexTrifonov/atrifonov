package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author atrifonov
* @since 09.07.2017
* @version 1
*/
public class PaintTest {
    /**
	* Test paint.
	*/
	@Test
    public void whenPiramidWithHeightTwoThenStringWithTwoRows() {
        Paint paint = new Paint();
        String result = paint.pyramid(2);
        String expected = String.format(" ^ %s^^^", System.getProperty("line.separator"));
        assertThat(result, is(expected));
    }

    /**
	* Test paint.
	*/
	@Test
    public void whenPiramidWithHeightThreeThenStringWithThreeRows() {
		Paint paint = new Paint();
        String result = paint.pyramid(3);
        String expected = String.format("  ^  %s ^^^ %s^^^^^", System.getProperty("line.separator"), System.getProperty("line.separator"));
        assertThat(result, is(expected));
    }

	/**
	* Test paint.
	*/
	@Test
    public void whenPiramidWithHeightOneThenStringWithOneRows() {
		Paint paint = new Paint();
        String result = paint.pyramid(1);
        String expected = new String("^");
        assertThat(result, is(expected));
    }

	/**
	* Test paint.
	*/
	@Test
    public void whenPiramidWithHeightZeroThenEmptyString() {
		Paint paint = new Paint();
        String result = paint.pyramid(0);
        String expected = new String("");
        assertThat(result, is(expected));
    }

	/**
	* Test paint.
	*/
	@Test
    public void whenPiramidWithHeightMinusOneThenEmptyString() {
		Paint paint = new Paint();
        String result = paint.pyramid(-1);
        String expected = new String("");
        assertThat(result, is(expected));
    }
}