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

    private String id;
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
