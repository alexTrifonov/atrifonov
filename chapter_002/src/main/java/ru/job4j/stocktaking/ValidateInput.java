package ru.job4j.stocktaking;

/**
 * Class for execute question. Execute invalid input.
 * @author atrifonov
 * @since 24.07.2017
 * @version 1
 */
public class ValidateInput extends ConsoleInput {


    @Override
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = - 1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Value key must be from menu. Please select key again.");
            } catch (NumberFormatException e) {
                System.out.println("Value key must be number. Please enter validate data again.");
            }
        } while(invalid);
        return value;
    }
}
