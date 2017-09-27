package ru.job4j.taskSynchronize;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Class for store users.
 * @author atrifonov.
 * @since 27.09.2017.
 * @version 1.
 */
@ThreadSafe
public class UserStorage {
    private ConcurrentHashMap<Integer, User> map = new ConcurrentHashMap<>();

    public void add(User user) {
        synchronized (this) {
            if (user != null && !map.containsKey(user.getId())) {
                map.put(user.getId(), user);
            }
        }

    }

    public boolean update(User newUser) {
        synchronized (this) {
            boolean success = false;
            if(newUser != null) {
                if(map.containsKey(newUser.getId())) {
                    map.remove(newUser.getId());
                    map.put(newUser.getId(), newUser);
                    success = true;
                }
            }
            return success;
        }

    }

    public void delete(User user) {
        synchronized (this) {
            if(user != null) {
                map.remove(user.getId());
            }
        }

    }

    public void transfer(int fromId, int toId, int amount) {
        synchronized (this) {
            User sourceUser = map.get(fromId);
            User destUser = map.get(toId);
            if(amount <= sourceUser.getAmount()) {
                sourceUser.setAmount(sourceUser.getAmount() - amount);
                destUser.setAmount(destUser.getAmount() + amount);
            } else {
                destUser.setAmount(destUser.getAmount() + sourceUser.getAmount());
                sourceUser.setAmount(0);
            }
        }


    }
}
