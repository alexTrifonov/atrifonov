package ru.job4j.taskGeneric;

/**
 * Class for model User.
 * @author atrifonov.
 * @since 23.08.2017.
 * @version 1.
 */
public class User extends Base {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
