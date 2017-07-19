package ru.job4j.inheritance;

/**
 * Class Class describe doctor.
 * @author atrifonov
 * @since 17.07.2017
 * @version 1
 */
public class Doctor extends Profession {

    /**
     * The king of diploma.
     */
    private String DiplomaMedicalInstitute;

    /**
     * The name of own clinic.
     */
    private String OwnClinic;

    /**
     * Constructs a new Doctor object, that has the name.
     * @param name The name of doctor.
     */
    public Doctor(String name) {
        super(name);
    }

    /**
     * Describe treating of man.
     * @param pacient The name of man.
     * @return The string describing the action of doctor. Doctor treat man.
     */
    public String treatPeople(Pacient pacient) {
        return "Доктор " + this.getName() + " лечит " + pacient;
    }

    /**
     * Describe advising of man.
     * @param pacient The name of man.
     * @return The string describing the action of doctor. Doctor treat man.
     */
    public String advisePeople(Pacient pacient) {
        return "Доктор " + this.getName() + " консультирует " + pacient;
    }

    /**
     * Describe making prescription.
     * @param prescription kind of illness.
     * @return The string describing the action of doctor. Doctor make prescription of illness treating.
     */
    public String givePrescription(Prescription prescription) {
        return "Доктор " + this.getName() + " выписывает " + prescription;
    }
}
