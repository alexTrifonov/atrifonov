package ru.job4j.SpringStore;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedList;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test.
 */
public class TestUserStorage {
    /**
     * Test add.
     */
    @Test
    public void whenMemoryStoreThenWriteInMemoryStore() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MemoryStore store = context.getBean(MemoryStore.class);
        UserStorage userStorage = new UserStorage(store);
        User user = new User("one");
        User userTwo = new User("two");
        userStorage.add(user);
        userStorage.add(userTwo);
        LinkedList<User> list = new LinkedList<>();
        list.add(user);
        list.add(userTwo);
        assertThat(list, is(userStorage.getUsers()));
    }
}
