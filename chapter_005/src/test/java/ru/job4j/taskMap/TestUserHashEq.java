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
public class TestUserHashEq {
    /**
     * Test.
     */
    @Test
    public void whenAddUsersHashEqlThenAllUsersWillAdd() {
        UserHashEq userHashEqA = new UserHashEq("Den", 2, new GregorianCalendar(1986, 11, 2));
        UserHashEq userHashEqB = new UserHashEq("Den", 2, new GregorianCalendar(1986, 11, 2));
        Map<UserHashEq, Object> mapHashEq = new HashMap<>();
        mapHashEq.put(userHashEqA, new Object());
        mapHashEq.put(userHashEqB, new Object());
        System.out.println(mapHashEq);
    }
}
