package ru.job4j.CarStoreBoot.domain;

import javax.persistence.*;

/**
 * Model for transmission of car.
 * @author atrifonov.
 * @version 1.
 * @since 26.04.2018.
 */
@Entity
@Table(name = "transmissions")
public class Transmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "transm_type")
    private String transmType;

    public Transmission() {}
    public Transmission(String transmType) {
        this.transmType = transmType;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transmission that = (Transmission) o;

        if (id != that.id) return false;
        return transmType != null ? transmType.equals(that.transmType) : that.transmType == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (transmType != null ? transmType.hashCode() : 0);
        return result;
    }
}