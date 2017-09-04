package ru.job4j.taskMap;

import org.junit.Test;
import ru.job4j.taskMap.User;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Test
 *
 * @author atrifonov.
 * @since 04.09.2017.
 * @version 1.
 */
public class TaskUser {
    /**
     * Test.
     */
    @Test
    public void whenAddUsersDefaultHashAndEqualThenAllUsersWillAdd() {
        Map<User, Object> map = new HashMap<>();
        User userA = new User("Dan", 3, new GregorianCalendar(1980, 10, 23));
        User userB = new User("Dan", 3, new GregorianCalendar(1980, 10, 23));

        map.put(userA, new Object());
        map.put(userB, new Object());
        System.out.println(map);
    }
}
