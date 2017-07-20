package ru.job4j.stockTaking;

import java.util.Scanner;

/**
 * Class Class for execute user input.
 * @author atrifonov
 * @since 20.07.2017
 * @version 1
 */
public class ConsoleInput implements Input {

    /**
     * For execute user input.
     * @param question Question for user.
     * @return user input.
     */
    @Override
    public String ask(String question) {
        String ask = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        if (question.equals("Enter name :") || question.equals("Enter description :")) {
            ask = scanner.nextLine();
        } else if (question.equals("Enter Id:") || question.equals("Select number. Enter number from 0 to 6")) {
            ask = scanner.next();
        }
        return ask;
    }
}
