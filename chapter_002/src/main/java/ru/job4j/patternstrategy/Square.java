package ru.job4j.patternstrategy;

/**
 * Class kind of Shape is Square.
 * @author atrifonov
 * @since 24.07.2017
 * @version 1
 */
public class Square implements Shape {
    /**
     * Create square string.
     * @return String that is has shape of square.
     */
    @Override
    public String pic() {
        StringBuilder sb = new StringBuilder();
        sb.append("++++\r\n");
        sb.append("++++\r\n");
        sb.append("++++\r\n");
        sb.append("++++");
        return sb.toString();
    }
}
