package ru.job4j.carstorespring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Model for engine of car.
 * @author atrifonov.
 * @version 1.
 * @since 28.02.2018.
 */
@Entity
@Table(name = "engines")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Engine engine = (Engine) o;

        if (id != engine.id) return false;
        return engineType != null ? engineType.equals(engine.engineType) : engine.engineType == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (engineType != null ? engineType.hashCode() : 0);
        return result;
    }
}
