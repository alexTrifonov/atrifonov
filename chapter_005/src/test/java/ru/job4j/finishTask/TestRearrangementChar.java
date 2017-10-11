package ru.job4j.finishTask;

import org.junit.Test;
import ru.job4j.finishTask.RearrangementChar;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 * @author atrifonov.
 * @version 1.
 * @since 11.10.2017.
 */
public class TestRearrangementChar {
    /**
     * Test check.
     */
    @Test
    public void whenOneWordContainOnlyAllLettersTwoWord() {
        RearrangementChar test = new RearrangementChar();
        assertThat(test.check("abfcdaef", "fdacefba"), is(true));
    }

    /**
     * Test check.
     */
    @Test
    public void whenOneWordAreNotRearrangementLettersTwoWord() {
        RearrangementChar test = new RearrangementChar();
        assertThat(test.check("abfcdaef", "fdacfefba"), is(false));
    }
}
