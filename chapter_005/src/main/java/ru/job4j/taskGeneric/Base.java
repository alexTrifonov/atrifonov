package ru.job4j.taskGeneric;

/**
 * Class for base for model.
 * @author atrifonov.
 * @since 23.08.2017.
 * @version 1.
 */
public abstract class Base {
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
