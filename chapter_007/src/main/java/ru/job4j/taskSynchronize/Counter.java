package ru.job4j.taskSynchronize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class Counter {

    @GuardedBy("this")
    private int count = 0;

    public int increment() {
        synchronized (this) {
            return count++;
        }

    }

    public int getCount() {
        synchronized (this) {
            return count;
        }
    }
}
