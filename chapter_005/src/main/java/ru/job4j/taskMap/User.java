package ru.job4j.taskMap;

import java.util.Calendar;

/**
 * Class for user for HashMap.
 * @author atrifonov.
 * @since 04.09.2017.
 * @version 1.
 */
public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public int getChildren() {
        return children;
    }

    public Calendar getBirthday() {
        return birthday;
    }
}
