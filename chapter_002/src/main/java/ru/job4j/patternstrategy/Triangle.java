package ru.job4j.patternstrategy;

/**
 * Class kind of Shape is Triangle.
 * @author atrifonov
 * @since 24.07.2017
 * @version 1
 */
public class Triangle implements Shape {
    /**
     * Create triangle string.
     * @return String that is has shape of triangle.
     */
    @Override
    public String pic() {
        StringBuilder sb = new StringBuilder();
        sb.append("  +  \r\n");
        sb.append(" +++ \r\n");
        sb.append("+++++");
        return sb.toString();
    }
}
