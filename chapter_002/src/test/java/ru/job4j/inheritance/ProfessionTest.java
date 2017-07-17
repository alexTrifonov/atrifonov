package ru.job4j.inheritance;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 17.07.2017.
 * @version 1.
 */
public class ProfessionTest {
    @Test
    /*
     * Test getName.
     */
    public void whenProfessionHasNameVasjaThenVasja() {
        Profession profession = new Profession("Vasja");
        String nameProfession = profession.getName();
        String expected = "Vasja";
        assertThat(nameProfession, is(expected));
    }
}
