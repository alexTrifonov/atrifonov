package ru.job4j.bank;

/**
 * Class for user account.
 * @author atrifonov.
 * @since 13.08.2017.
 * @version 1.
 */
public class Account {
    /**
     * Amount money on account.
     */
    double value;
    /**
     * Requisites of account.
     */
    int requisites;

    /**
     * Construct account.
     * @param value amount money on account.
     * @param requisites requisites of account.
     */
    public Account(double value, int requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public int getRequisites() {
        return requisites;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
