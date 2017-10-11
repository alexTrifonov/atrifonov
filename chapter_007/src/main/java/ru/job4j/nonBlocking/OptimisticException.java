package ru.job4j.nonBlocking;

/**
 * Class exception for incorrect update.
 * @author atrifonov.
 * @version 1.
 * @since 11.10.2017.
 */
public class OptimisticException extends RuntimeException {
    /**
     * Construct OptimisticException with message.
     * @param message message of exception.
     */
    public OptimisticException(String message) {
        super(message);
    }
}
