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
public class DoctorTest {
    @Test
    /*
     * Test treatPeople.
     */
    public void whenDoctorTreatPeopleThenStringAboutThis() {
        Doctor doctor = new Doctor("Mike");
        String doctorTreatPeople = doctor.treatPeople("Vasja");
        String expected = "Доктор Mike лечит Vasja";
        assertThat(doctorTreatPeople, is(expected));
    }

    @Test
    /*
     * Test advisePeople.
     */
    public void whenDoctorAdvisePeopleThenStringAboutThis() {
        Doctor doctor = new Doctor("Mike");
        String doctorAdvisePeople = doctor.advisePeople("Vasja");
        String expected = "Доктор Mike консультирует Vasja";
        assertThat(doctorAdvisePeople, is(expected));
    }

    @Test
    /*
     * Test givePrescription.
     */
    public void whenDoctorGivePrescriptionThenStringAboutThis() {
        Doctor doctor = new Doctor("Mike");
        String doctoriGvePrescription = doctor.givePrescription("treatmentOfGrippe");
        String expected = "Доктор Mike выписывает treatmentOfGrippe";
        assertThat(doctoriGvePrescription, is(expected));
    }
}
