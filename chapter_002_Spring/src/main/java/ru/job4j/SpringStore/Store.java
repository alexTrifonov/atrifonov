package ru.job4j.SpringStore;

import java.util.List;

/**
 * Store of users.
 */
public interface Store {
    void addUser(User user);
    List<User> getUsers();
}
