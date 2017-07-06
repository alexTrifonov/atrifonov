package ru.job4j.condition;

import org.junit.Test;

//import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
*
* @author atrifonov.
* @version 1.
* @since 04.07.2017
*/
public class TriangleTest {
	/**
	* Test lengthEdge.
	*/
	@Test
	public void whenLengthEdgePointZeroZeroPointZeroTwoThenTwo() {
		Point a = new Point(0, 0);
		Point b = new Point(0, 2);
		Point c = new Point(2, 0);

		Triangle triangle = new Triangle(a, b, c);
		double result = triangle.lengthEdge(a, b);
		double expected = 2D;
		assertThat(result, closeTo(expected, 0.1));
	}

	/**
	* Test lengthEdge.
	*/
	@Test
	public void whenLengthEdgePointZeroZeroPointTwoZeroThenTwo() {
		Point a = new Point(0, 0);
		Point b = new Point(0, 2);
		Point c = new Point(2, 0);

		Triangle triangle = new Triangle(a, b, c);
		double result = triangle.lengthEdge(a, c);
		double expected = 2D;
		assertThat(result, closeTo(expected, 0.1));
	}

	/**
	* Test lengthEdge.
	*/
	@Test
	public void whenLengthEdgePointZeroZeroPointZeroZeroThenTwo() {
		Point a = new Point(0, 0);
		Point b = new Point(0, 0);
		Point c = new Point(0, 0);

		Triangle triangle = new Triangle(a, b, c);
		double result = triangle.lengthEdge(a, b);
		double expected = 0D;
		assertThat(result, closeTo(expected, 0.1));
	}

	/**
	* Test area.
	*/
	@Test
	public void whenAreaSetThreePointThenTriangleArea() {
		//create three object Point.
		Point a = new Point(0, 0);
		Point b = new Point(0, 2);
		Point c = new Point(2, 0);
		//create object Triangle.
		Triangle triangle = new Triangle(a, b, c);
		//calculate area.
		double result = triangle.area();
		//assign expected result.
		double expected = 2D;
		//check result and expected result.
		assertThat(result, closeTo(expected, 0.1));
	}

	/**
	* Test area.
	*/
	@Test
	public void whenAreaSetThreePointWithMinusXThenTriangleArea() {
		//create three object Point.
		Point a = new Point(-2, 2);
		Point b = new Point(0, 2);
		Point c = new Point(0, 0);
		//create object Triangle.
		Triangle triangle = new Triangle(a, b, c);
		//calculate area.
		double result = triangle.area();
		//assign expected result.
		double expected = 2D;
		//check result and expected result.
		assertThat(result, closeTo(expected, 0.1));
	}

	/**
	* Test area.
	*/
	@Test
	public void whenAreaSetThreePointOnLineThenTriangleArea() {
		//create three object Point.
		Point a = new Point(0, 0);
		Point b = new Point(0, 1);
		Point c = new Point(0, 3);
		//create object Triangle.
		Triangle triangle = new Triangle(a, b, c);
		//calculate area.
		double result = triangle.area();
		//assign expected result.
		double expected = 0D;
		//check result and expected result.
		assertThat(result, closeTo(expected, 0.1));
	}

	/**
	* Test area.
	*/
	@Test
	public void whenAreaSetThreeAnotherPointThenTriangleArea() {
		//create three object Point.
		Point a = new Point(0, 3);
		Point b = new Point(3, 3);
		Point c = new Point(3, 0);
		//create object Triangle.
		Triangle triangle = new Triangle(a, b, c);
		//calculate area.
		double result = triangle.area();
		//assign expected result.
		double expected = 4.5;
		//check result and expected result.
		assertThat(result, closeTo(expected, 0.1));
	}
}