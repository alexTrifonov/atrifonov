package ru.job4j.carstore;

/**
 * Model for automobile model.
 * @author atrifonov.
 * @version 1.
 * @since 28.02.2018.
 */
public class AutoModel {
    private int id;
    private String model;
    private MakeCar makeCar;

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public MakeCar getMakeCar() {
        return makeCar;
    }

    public void setMakeCar(MakeCar makeCar) {
        this.makeCar = makeCar;
    }
}
