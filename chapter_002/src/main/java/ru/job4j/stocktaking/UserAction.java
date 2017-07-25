package ru.job4j.stocktaking;

/**
 * Class for create action.
 * @author atrifonov
 * @since 25.07.2017
 * @version 1
 */
public interface UserAction {
    /**
     * Method for get number of action
     * @return Number of action.
     */
    int key();

    /**
     * Execute action.
     * @param input Object for execute user input.
     * @param tracker Object for store items.
     */
    void execute(Input input, Tracker tracker);

    /**
     * Describe of action.
     * @return Describe of action.
     */
    String info();
}
