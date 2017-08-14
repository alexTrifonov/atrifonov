package ru.job4j.bank;


/**
 * Class for user.
 * @author atrifonov.
 * @since 13.08.2017.
 * @version 1.
 */
public class User {
    /**
     * Name of user.
     */
    private String name;
    /**
     * Passport of user.
     */
    private int passport;

    /**
     * Construct user.
     * @param name name of user.
     * @param passport passport of user.
     */
    public User(String name, int passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public int getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (this.passport != user.passport) return false;
        return this.name != null ? name.equals(user.name) : user.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + passport;
        return result;
    }

}
