package ru.job4j.begin;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 06.08.2017.
 * @version 1.
 */
public class TestUserConvert {
    /**
     * Test process.
     */
    @Test
    public void whenListWithThreeUserThenHashMapWithThreePair() {
        User a = new User(1, "a", "Spb");
        User b = new User(2, "b", "Ekb");
        User c = new User(3, "c", "Msk");
        List<User> userList = new ArrayList<>(3);
        userList.add(a);
        userList.add(b);
        userList.add(c);
        UserConvert userConvert = new UserConvert();
        HashMap<Integer, User> hashMapResult = userConvert.process(userList);
        HashMap<Integer, User> hashMapExpected = new HashMap<>();
        hashMapExpected.put(1, a);
        hashMapExpected.put(2, b);
        hashMapExpected.put(3, c);
        assertThat(hashMapResult, is(hashMapExpected));
    }
}
