package ru.job4j.CarStoreBoot.domain;

import javax.persistence.*;

/**
 * Model for make of car.
 * @author atrifonov.
 * @version 1.
 * @since 26.04.2018.
 */
@Entity
@Table(name = "make_cars")
public class MakeCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String make;

    public MakeCar(){

    }

    public MakeCar(String make) {
        this.make = make;
    }

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

    @Override
    public String toString() {
        return make;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MakeCar makeCar = (MakeCar) o;

        if (id != makeCar.id) return false;
        return make != null ? make.equals(makeCar.make) : makeCar.make == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (make != null ? make.hashCode() : 0);
        return result;
    }
}