package ru.job4j.stocktaking;

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
}
