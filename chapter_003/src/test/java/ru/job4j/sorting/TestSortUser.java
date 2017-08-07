package ru.job4j.sorting;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.*;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 07.08.2017.
 * @version 1.
 */
public class TestSortUser {
    /**
     * Test sort.
     */
    @Test
    public void whenListWithThreeUserThenSortedByAgeSetWithThreeUser() {
        User user = new User("Ilya", 20);
        User user1 = new User("Petr", 30);
        User user2 = new User("Ivan", 20);
        User user3 = new User("Yan", 40);

        List<User> list = new ArrayList<>(5);
        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(null);

        SortUser sortUser = new SortUser();
        Set<User> resultSortedSetUsers = sortUser.sort(list);
        List<User> sortedListUser = new ArrayList<>(4);
        sortedListUser.add(user);
        sortedListUser.add(user2);
        sortedListUser.add(user1);
        sortedListUser.add(user3);
        Iterator<User> iteratorSet = resultSortedSetUsers.iterator();
        boolean setEqualList = true;
        for(User x: sortedListUser) {
            setEqualList = (x == iteratorSet.next());
        }
        assertThat(setEqualList, is(true));
    }
}
