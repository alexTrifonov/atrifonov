package ru.job4j.inheritance;

/**
 * Created by Alexandr on 16.07.2017.
 */
public class Profession {
    /**
     * The seniority on profession.
     */
    private int Seniority;
    /**
     * The name of man.
     */
    private String name;

    /**
     * Constructs the man of this profession that has name.
     * @param name
     */
    public Profession(String name) {
        this.name = name;
    }

    /**
     * Getter of name.
     * @return The name of man.
     */
    public String getName() {
        return this.name;
    }
}
