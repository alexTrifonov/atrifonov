package ru.job4j.CarStoreBoot.web;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.CarStoreBoot.domain.User;
import ru.job4j.CarStoreBoot.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Test of controller Users.
 *
 * @author atrifonov.
 * @version 1.
 * @since 11.05.2018.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(Users.class)
public class UsersTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    private UserService userService;

    /**
     * Test getUsers.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "Vasja", roles = {"USER", "ADMIN"})
    public void whenGetUsersThenPageUsers() throws Exception {
        User user = new User("Oleg", "pass");
        User userOne = new User("Den", "password");
        List<User> list = new ArrayList<User>(Lists.newArrayList(user, userOne));
        given(
                this.userService.findAll()
        ).willReturn(list);

        this.mvc.perform(
                get("/users").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("users")
        ).andExpect(
                model().attribute("users", list)
        );
    }
}
