package ru.job4j.patternstrategy;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 24.07.2017.
 * @version 1.
 */
public class PaintTest {
    /**
     * Test draw.
     */
    @Test
    public void whenDrawSquareThenPrintSquare() {
        Paint paint = new Paint();
        Shape square = new Square();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        paint.draw(square);
        String squareString = "++++\r\n++++\r\n++++\r\n++++\r\n";
        assertThat(out.toString(), is(squareString));
    }

    /**
     * Test draw.
     */
    @Test
    public void whenDrawTriangleThenPrintTriangle() {
        Paint paint = new Paint();
        Shape triangle = new Triangle();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        paint.draw(triangle);
        String triangleString = "  +  \r\n +++ \r\n+++++\r\n";
        assertThat(out.toString(), is(triangleString));
    }
}
