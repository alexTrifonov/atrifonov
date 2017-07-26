package ru.job4j.stocktaking;

/**
 * Class for exception choice nonexistent menu item.
 * @author atrifonov
 * @since 26.07.2017
 * @version 1
 */
public class MenuOutException extends RuntimeException {
    public MenuOutException(String message) {
        super(message);
    }
}
