package ru.job4j.inheritance;

/**
 * Class Class describe engineer.
 * @author atrifonov
 * @since 17.07.2017
 * @version 1
 */
public class Engineer extends Profession {

    /**
     * The name of laboratory.
     */
    private String Laboratory;

    /**
     * The name of assembly shop.
     */
    private String AssemblyShop;

    /**
     * Constructs a new Engineer object, that has the name.
     * @param name The name of Engineer.
     */
    public Engineer(String name) {
        super(name);
    }

    /**
     * Describe design of device.
     * @param device The name of device.
     * @return The string describing the action of engineer. Engineer design of device.
     */
    public String designDevice(String device) {
        return "Инженер " + this.getName() + " разрабатывает " + device;
    }

    /**
     * Describe doing of test.
     * @param test The name of test.
     * @return The string describing the action of engineer. Engineer doing of test.
     */
    public String doTest(String test) {
        return "Инженер " + this.getName() + " проводит испытание под названием " + test;
    }

    /**
     * Describe making of documentation.
     * @param documentation The kind of documentation.
     * @return The string describing the action of engineer. Engineer making of documentation.
     */
    public String makeDocumentation(String documentation) {
        return "Инженер " + this.getName() + " пишет документацию под названием " + documentation;
    }
}
