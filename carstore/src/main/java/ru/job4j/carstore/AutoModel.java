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

    public AutoModel() {

    }

    public AutoModel(String model, MakeCar makeCar) {
        this.model = model;
        this.makeCar = makeCar;
    }

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

    @Override
    public String toString() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoModel autoModel = (AutoModel) o;

        if (id != autoModel.id) return false;
        if (model != null ? !model.equals(autoModel.model) : autoModel.model != null) return false;
        return makeCar != null ? makeCar.equals(autoModel.makeCar) : autoModel.makeCar == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (makeCar != null ? makeCar.hashCode() : 0);
        return result;
    }
}
