package ru.job4j.carstore;

/**
 * Model for make of car.
 * @author atrifonov.
 * @version 1.
 * @since 28.02.2018.
 */
public class MakeCar {
    private int id;
    private String make;

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMake(String make) {
        this.make = make;
    }
}
