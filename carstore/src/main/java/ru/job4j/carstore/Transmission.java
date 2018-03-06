package ru.job4j.carstore;

/**
 * Model for transmission of car.
 * @author atrifonov.
 * @version 1.
 * @since 28.02.2018.
 */
public class Transmission {
    private int id;
    private String transmType;

    public void setId(int id) {
        this.id = id;
    }

    public void setTransmType(String transmType) {
        this.transmType = transmType;
    }

    public int getId() {

        return id;
    }

    public String getTransmType() {
        return transmType;
    }

    @Override
    public String toString() {
        return transmType;
    }
}
