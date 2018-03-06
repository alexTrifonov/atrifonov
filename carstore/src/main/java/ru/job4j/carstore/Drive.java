package ru.job4j.carstore;

/**
 * Model for drive of car.
 * @author atrifonov.
 * @version 1.
 * @since 28.02.2018.
 */
public class Drive {
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
}
