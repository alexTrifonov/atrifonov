package ru.job4j.patternstrategy;

/**
 * Class for shape print in console.
 * @author atrifonov
 * @since 24.07.2017
 * @version 1
 */
public class Paint {
    /**
     * Shape of printing object.
     */
    private Shape shape;

    /**
     * Print shape in console.
     * @param shape Kind of shape.
     */
    public void draw(Shape shape) {
        System.out.println(shape.pic());
    }
}
