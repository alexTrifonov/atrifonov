package ru.job4j.carstore;

/**
 * Model for body of car.
 * @author atrifonov.
 * @version 1.
 * @since 28.02.2018.
 */
public class Body {
    private int id;
    private String bodyType;

    public int getId() {
        return id;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
