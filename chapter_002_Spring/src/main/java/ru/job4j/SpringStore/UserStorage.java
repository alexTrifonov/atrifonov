package ru.job4j.SpringStore;

import java.util.List;

/**
 * Store.
 */
public class UserStorage {
    private Store store;

    public UserStorage(Store store) {
        this.store = store;
    }

    public void add(User user) {
        store.addUser(user);
    }

    public List<User> getUsers() {
        return store.getUsers();
    }
}
