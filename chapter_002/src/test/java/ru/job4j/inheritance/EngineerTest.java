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
public class EngineerTest {
    @Test
    /*
     * Test designDevice.
     */
    public void whenEngineerDesignDeviceThenStringAboutThis() {
        Engineer engineer = new Engineer("Ivan Ivanych");
        String engineerDesignDevice = engineer.designDevice("Sensor");
        String expected = "Инженер Ivan Ivanych разрабатывает Sensor";
        assertThat(engineerDesignDevice, is(expected));
    }

    @Test
    /*
     * Test doTest.
     */
    public void whenEngineerDoTestThenStringAboutThis() {
        Engineer engineer = new Engineer("Ivan Ivanych");
        String engineerDoTest = engineer.doTest("heat chamber");
        String expected = "Инженер Ivan Ivanych проводит испытание под названием heat chamber";
        assertThat(engineerDoTest, is(expected));
    }

    @Test
    /*
     * Test makeDocumentation.
     */
    public void whenEngineerMakeDocumentationThenStringAboutThis() {
        Engineer engineer = new Engineer("Ivan Ivanych");
        String engineerMakeDocumentation = engineer.makeDocumentation("service manual");
        String expected = "Инженер Ivan Ivanych пишет документацию под названием service manual";
        assertThat(engineerMakeDocumentation, is(expected));
    }
}
