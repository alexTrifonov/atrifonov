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
    public void whenListFourUserAndNullThenSortedByAgeSetWithFourUser() {
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

    /**
     * Test sortNameLength.
     */
    @Test
    public void whenListWithSixUserAndThreeNullThenSortListByNameLength() {
        User user = new User("John", 25);
        User user1 = new User("Gram", 30);
        User user2 = new User("Yan", 20);
        User user3 = new User("Yan", 25);
        User user4 = new User("Gram", 25);
        User user5 = new User("Gram", 20);

        List<User> list = new ArrayList<>(3);
        list.add(null);
        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(null);
        list.add(user4);
        list.add(user5);
        list.add(null);

        SortUser sortUser = new SortUser();

        List<User> resultList = sortUser.sortNameLength(list);
        List<User> expectedList = new ArrayList<>(9);
        expectedList.add(user2);
        expectedList.add(user3);
        expectedList.add(user);
        expectedList.add(user1);
        expectedList.add(user4);
        expectedList.add(user5);
        expectedList.add(null);
        expectedList.add(null);
        expectedList.add(null);

        Iterator<User> userResultIterator = resultList.iterator();
        boolean resultEqualExpected = true;
        for(User x : expectedList) {
            resultEqualExpected = (x == userResultIterator.next());
        }
        assertThat(resultEqualExpected, is(true));
    }

    /**
     * Test sortByAllFields.
     */
    @Test
    public void whenListWithSixUserAndThreeNullThenSortListByAllFields() {
        User user = new User("John", 25);
        User user1 = new User("Gram", 30);
        User user2 = new User("Yan", 20);
        User user3 = new User("Yan", 25);
        User user4 = new User("Gram", 25);
        User user5 = new User("Gram", 20);

        List<User> list = new ArrayList<>(3);
        list.add(null);
        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(null);
        list.add(user4);
        list.add(user5);
        list.add(null);

        SortUser sortUser = new SortUser();

        List<User> resultList = sortUser.sortByAllFields(list);
        List<User> expectedList = new ArrayList<>(9);
        expectedList.add(user2);
        expectedList.add(user3);
        expectedList.add(user5);
        expectedList.add(user);
        expectedList.add(user4);
        expectedList.add(user1);
        expectedList.add(null);
        expectedList.add(null);
        expectedList.add(null);

        Iterator<User> userIterator = resultList.iterator();
        boolean resultEqualExpected = true;
        for(User x : expectedList) {
            resultEqualExpected = (x == userIterator.next());
        }
        assertThat(resultEqualExpected, is(true));
    }
}
