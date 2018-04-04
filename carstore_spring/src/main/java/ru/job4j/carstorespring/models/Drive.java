package ru.job4j.carstorespring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Model for drive of car.
 * @author atrifonov.
 * @version 1.
 * @since 28.02.2018.
 */
@Entity
@Table(name = "drives")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Drive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String driveType;

    public int getId() {
        return id;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    @Override
    public String toString() {
        return driveType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Drive drive = (Drive) o;

        if (id != drive.id) return false;
        return driveType != null ? driveType.equals(drive.driveType) : drive.driveType == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (driveType != null ? driveType.hashCode() : 0);
        return result;
    }
}
