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
public class TestUserEql {
    /**
     * Test.
     */
    @Test
    public void whenAddUsersEqlThenAllUsersWillAdd() {
        UserEql userEqlA = new UserEql("Ben", 2, new GregorianCalendar(1986, 11, 2));
        UserEql userEqlB = new UserEql("Ben", 2, new GregorianCalendar(1986, 11, 2));
        Map<UserEql, Object> mapEql = new HashMap<>();
        mapEql.put(userEqlA, new Object());
        mapEql.put(userEqlB, new Object());
        System.out.println(mapEql);
    }
}
