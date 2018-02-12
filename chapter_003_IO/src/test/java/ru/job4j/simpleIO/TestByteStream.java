package ru.job4j.simpleIO;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author atrifonov.
 * @version 1.
 * @since 12.02.2018.
 */
public class TestByteStream {

    /**
     * Test isNumber.
     */
    @Test
    public void whenInputStreamHasEvenNumberThenTrue() {
        ByteStream byteStream = new ByteStream();
        byte[] buffer = {1,2,3};
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
        boolean isNumber = byteStream.isNumber(byteArrayInputStream);
        assertThat(isNumber, is(true));
    }

    /**
     * Test isNumber.
     */
    @Test
    public void whenInputStreamHasNotEvenNumberThenFalse() {
        ByteStream byteStream = new ByteStream();
        byte[] buffer = {1,3,5,7};
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
        boolean isNumber = byteStream.isNumber(byteArrayInputStream);
        assertThat(isNumber, is(false));
    }
}
