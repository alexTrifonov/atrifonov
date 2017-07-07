package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author atrifonov
* @since 04.07.2017
* @version 1
*/
public class BoardTest {

	/**
	* Test paint.
	*/
	@Test
    public void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
        Board board = new Board();
        String result = board.paint(3, 3);
        final String line = System.getProperty("line.separator");
        String expected = String.format("x x%s x %sx x%s", line, line, line);
        assertThat(result, is(expected));
 }

	/**
	* Test paint.
	*/
	@Test
    public void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
        Board board = new Board();
		String result = board.paint(5, 4);
		final String line = System.getProperty("line.separator");
		String expected = String.format("x x x%s x x %sx x x%s x x %s", line, line, line, line);
		assertThat(result, is(expected));
    }

	/**
	* Test paint.
	*/
	@Test
    public void whenPaintBoardWithWidthZeroAndHeightZeroThenEmptyString() {
        Board board = new Board();
		String result = board.paint(0, 0);
		String expected = "";
		assertThat(result, is(expected));
    }

	/**
	* Test paint.
	*/
	@Test
    public void whenPaintBoardWithWidthOneAndHeightOneThenStringWithOneColsAndOneRows() {
        Board board = new Board();
		String result = board.paint(1, 1);
		final String line = System.getProperty("line.separator");
		String expected = String.format("x%s", line);
		assertThat(result, is(expected));
    }

	/**
	* Test paint.
	*/
	@Test
    public void whenPaintBoardWithWidthOneAndHeightZeroThenEmptyString() {
        Board board = new Board();
		String result = board.paint(1, 0);
		String expected = "";
		assertThat(result, is(expected));
    }

	/**
	* Test paint.
	*/
	@Test
    public void whenPaintBoardWithWidthZeroAndHeightOneThenSeparatorString() {
        Board board = new Board();
		String result = board.paint(0, 1);
		String expected = System.getProperty("line.separator");
		assertThat(result, is(expected));
    }

	/**
	* Test paint.
	*/
	@Test
    public void whenPaintBoardWithWidthMinusThreeAndHeightMinusThreeThenEmptyString() {
        Board board = new Board();
		String result = board.paint(-3, -3);
		String expected = "";
		assertThat(result, is(expected));
    }
}