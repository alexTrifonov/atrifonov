package ru.job4j.simpleIO;

import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test DropAbuse.
 * @author atrifonov.
 * @version 1.
 * @since 12.02.2018.
 */
public class TestDropAbuse {

    /**
     * Test dropAbuse.
     */
    @Test
    public void whenTextHasAbuseThenToDeleteAbuse() {
        String st = "Returns the index within this String of the last occurrence of the specified substring\r\n" +
                "Replaces the first substring of this string that matches the given regular expression with the given replacement.";
        String[] abuse = {"ret", "string", "occur", "of"};

        Reader reader = new StringReader(st);

        Writer writer = new StringWriter();
        DropAbuse dropAbuse = new DropAbuse();
        dropAbuse.dropAbuse(reader, writer, abuse);
        String cleanString = writer.toString();
        boolean hasAbuse = cleanString.contains("Ret") || cleanString.contains("String")
                || cleanString.contains("string") || cleanString.contains("occur") || cleanString.contains("of");
        assertThat(hasAbuse, is(false));
    }
}
