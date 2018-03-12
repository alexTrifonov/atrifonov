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

    @Override
    public String toString() {
        return bodyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Body body = (Body) o;

        if (id != body.id) return false;
        return bodyType != null ? bodyType.equals(body.bodyType) : body.bodyType == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bodyType != null ? bodyType.hashCode() : 0);
        return result;
    }
}
