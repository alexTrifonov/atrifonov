package ru.job4j.taskMap;

import org.junit.Test;

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
public class TaskUserHash {
    /**
     * Test.
     */
    @Test
    public void whenAddUsersHashThenAllUsersWillAdd() {
        Map<UserHash, Object> mapOnHash = new HashMap<>();
        UserHash userHashA = new UserHash("Dan", 3, new GregorianCalendar(1980, 10, 23));
        UserHash userHashB = new UserHash("Dan", 3, new GregorianCalendar(1980, 10, 23));
        mapOnHash.put(userHashA, new Object());
        mapOnHash.put(userHashB, new Object());
        System.out.println(mapOnHash);
    }
}
