package ru.job4j.taskSynchronize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Class for UserStorage.
 * @author atrifonov.
 * @since 27.09.2017.
 * @version 1.
 */
@ThreadSafe
public class User {
    /**
     * Id of user
     */
    private int id;
    /**
     * Account of user.
     */
    @GuardedBy("this")
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        synchronized (this) {
            this.amount = amount;
        }
    }
}
