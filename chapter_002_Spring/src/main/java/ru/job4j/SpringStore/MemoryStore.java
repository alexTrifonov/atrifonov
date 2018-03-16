package ru.job4j.SpringStore;

import java.util.LinkedList;
import java.util.List;

/**
 * Store in memory.
 */
public class MemoryStore implements Store {
    private int count = 0;
    private LinkedList<User> list = new LinkedList<>();

    @Override
    public void addUser(User user) {
        count++;
        user.setId(count);
        list.add(user);
    }

    @Override
    public List<User> getUsers() {
        return list;
    }
}
