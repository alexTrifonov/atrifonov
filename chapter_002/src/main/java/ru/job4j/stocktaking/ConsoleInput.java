package ru.job4j.stocktaking;

import java.util.Scanner;

/**
 * Class Class for execute user input.
 * @author atrifonov
 * @since 20.07.2017
 * @version 1
 */

public class ConsoleInput implements Input {

    /**
     * Object for receiving user ask.
     */
    private Scanner scanner = new Scanner(System.in);
    /**
     * For execute user input.
     * @param question Question for user.
     * @return user input.
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
