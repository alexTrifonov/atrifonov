package ru.job4j.carstore;

/**
 * Model for engine of car.
 * @author atrifonov.
 * @version 1.
 * @since 28.02.2018.
 */
public class Engine {
    private int id;
    private String engineType;

    public int getId() {
        return id;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return engineType;
    }
}
