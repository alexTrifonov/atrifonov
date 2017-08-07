package ru.job4j.sorting;

/**
 * Class user for sorting.
 * @author atrifonov.
 * @since 07.08.2017.
 * @version 1.
 */
public class User implements Comparable<User> {
    /**
     *The name of user.
     */
    private String name;
    /**
     * The age of user.
     */
    private int age;

    /**
     * Constructs a user with specified name and age.
     * @param name the name of user.
     * @param age the age of user.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Compares user with another user by age and name.
     * @param o another user.
     * @return 1
     */
    @Override
    public int compareTo(User o) {
        int compareAge = this.age - o.age;
        if (compareAge == 0) {
            compareAge = this.name.compareTo(o.name);
        }
        return compareAge;
    }


}
