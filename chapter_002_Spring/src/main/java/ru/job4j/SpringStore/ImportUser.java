package ru.job4j.SpringStore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Application.
 */
public class ImportUser {
    public static void main(String[] args) {
        User user = new User("Vasiliy Ivanov");
        User userTwo = new User("Pavel Rogov");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        UserStorage userStorage = context.getBean(UserStorage.class);
        userStorage.add(user);
        userStorage.add(userTwo);

        List<User> users = userStorage.getUsers();
        for (User x : users) {
            System.out.println(String.format("id = %d, name = %s", x.getId(), x.getName()));
        }
    }
}
