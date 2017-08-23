package ru.job4j.taskGeneric;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test
 *
 * @author atrifonov.
 * @since 23.08.2017.
 * @version 1.
 */
public class TestUserStore {
    /**
     * Test add and delete.
     */
    @Test
    public void whenAddTwoRoleAndDeleteSecondItemThenWillGetSecondItem() {
        UserStore userStore = new UserStore();
        User userOne = new User("a");
        Random r = new Random(1000);
        userOne.setId(System.currentTimeMillis() + r.nextInt() + "");

        User userTwo = new User("b");
        String timeUserTwo = System.currentTimeMillis() + r.nextInt() + "";
        userTwo.setId(timeUserTwo);

        userStore.add(userOne);
        userStore.add(userTwo);

        User deletedUser = userStore.delete(userTwo);
        boolean equalRT = deletedUser.getId().equals(timeUserTwo) &&
                deletedUser.getName().equals("b");

        assertThat(equalRT, is(true));
    }

    /**
     * Test add, update and delete.
     */
    @Test
    public void whenAddThreeRoleAndUpdateSecondItemAndDeleteSecondItemThenWillGetSecondItem() {
        UserStore userStore = new UserStore();
        User userOne = new User("a");
        Random r = new Random(1000);
        userOne.setId(System.currentTimeMillis() + r.nextInt() + "");

        User userTwo = new User("b");
        String timeUserTwo = System.currentTimeMillis() + r.nextInt() + "";
        userTwo.setId(timeUserTwo);

        User userThree = new User("c");
        userThree.setId(System.currentTimeMillis() + r.nextInt() + "");

        userStore.add(userOne);
        userStore.add(userTwo);
        userStore.add(userThree);

        User userFour = new User("d");
        userFour.setId(timeUserTwo);

        userStore.update(userFour);

        User deletedUser = userStore.delete(userFour);
        boolean equalRT = deletedUser.getId().equals(timeUserTwo) &&
                deletedUser.getName().equals("d");

        assertThat(equalRT, is(true));
    }
}
