package ru.job4j.stocktaking;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Interface for user input.
 * @author atrifonov
 * @since 20.07.2017
 * @version 1
 */
public interface Input {
    /**
     * For execute user input.
     * @param question Question for user.
     * @return user input.
     */
    String ask(String question);

    /**
     * For execute integer user input
     * @param question Question for user.
     * @param range Range of possible integers.
     * @return user input as int value.
     */
    int ask(String question, List<Integer> range);
}
