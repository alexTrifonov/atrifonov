package ru.job4j.begin;

/**
 * Class for user.
 * @author atrifonov.
 * @since 06.08.2017.
 * @version 1.
 */
public class User {
    /**
     * User id.
     */
    private int Id;
    /**
     * User name.
     */
    private String name;
    /**
     * User city.
     */
    private String city;

    /**
     * Construct user with Id, name and city.
     * @param Id User id.
     * @param name User name.
     * @param city User city.
     */
    public User(int Id, String name, String city) {
        this.Id = Id;
        this.name = name;
        this.city = city;
    }

    /**
     * Getter for Id.
     * @return Id.
     */
    public int getId() {
        return this.Id;
    }
}
