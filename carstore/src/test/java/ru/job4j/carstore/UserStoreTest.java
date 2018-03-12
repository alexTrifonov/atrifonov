package ru.job4j.carstore;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test for UserStore.
 */
public class UserStoreTest {
    private final UserStore userStore = UserStore.INSTANCE;
    /**
     * Test for getUser.
     */
    @Test
    public void testGetUser() {
        User user = new User();
        String login = "vasja";
        user.setLogin(login);
        user.setPassword("pass");
        user = userStore.add(user);
        User userFromBase = userStore.getUser(login);
        assertThat(user, is(userFromBase));
    }

    /**
     * Test for isCredential.
     */
    @Test
    public void testIsCredential() {
        User userOne = new User();
        userOne.setLogin("UserOne");
        userOne.setPassword("password");
        User userTwo = new User();
        userTwo.setLogin("UserTwo");
        userTwo.setPassword("pass");
        userStore.add(userOne);
        userStore.add(userTwo);
        boolean isCredential = userStore.isCredential("UserTwo", "pass");
        assertThat(isCredential, is(true));
    }
}
