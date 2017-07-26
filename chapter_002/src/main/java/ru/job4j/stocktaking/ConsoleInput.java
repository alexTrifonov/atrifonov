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

    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range.");
        }
    }
}
