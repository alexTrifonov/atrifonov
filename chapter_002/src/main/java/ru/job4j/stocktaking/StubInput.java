package ru.job4j.stocktaking;

/**
 * Class for execute question. Contain prepared answer.
 * @author atrifonov
 * @since 24.07.2017
 * @version 1
 */
public class StubInput implements Input {
    /**
     * Array of answers;
     */
    private String[] answers;

    /**
     * Index of current answer.
     */
    private int position = 0;

    /**
     * Construct object StubInput with answers array.
     * @param answers Array of answers.
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * For return prepared answer.
     * @param question Question for object StubInput.
     * @return prepared answer.
     */
    @Override
    public String ask(String question) {
        return answers[position++];
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
