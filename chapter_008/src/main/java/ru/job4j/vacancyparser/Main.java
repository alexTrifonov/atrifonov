package ru.job4j.vacancyparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Entry point of application.
 * @author atrifonov.
 * @version 1.
 * @since 27.11.2017.
 */
public class Main {
    /**
     * Logger for logging to database.
     */
    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class.getName());

    /**
     * psvm.
     * @param args arguments.
     */
    public static void main(String[] args) {
        LOGGER.trace("Trace Message!");
        LOGGER.debug("Debug Message!");
        LOGGER.info("Info Message!");
        LOGGER.warn("Warn Message!");
        LOGGER.error("Error Message!");
        LOGGER.fatal("Fatal Message!");
    }
}
