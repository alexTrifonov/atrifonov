package ru.job4j.taskMap;

import java.util.Calendar;

/**
 * Class for user with overriding hashCode() for HashMap.
 * @author atrifonov.
 * @since 04.09.2017.
 * @version 1.
 */
public class UserHash extends User {
    public UserHash(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public int hashCode() {
        return getName().hashCode() * 31 + getChildren() + getBirthday().hashCode() * 31;
    }


}