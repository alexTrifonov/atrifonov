package ru.job4j.CarStoreBoot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.CarStoreBoot.Application;
import ru.job4j.CarStoreBoot.domain.Role;
import ru.job4j.CarStoreBoot.domain.User;
import ru.job4j.CarStoreBoot.service.RoleService;
import ru.job4j.CarStoreBoot.service.UserService;
import ru.job4j.CarStoreBoot.utils.PsqlUserDetailsService;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * Test of controller ActionsOnUser
 *
 * @author atrifonov
 * @version 1.
 * @since 11.05.2018
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ActionsOnUser.class)
@ContextConfiguration(classes = {Application.class, MvcConfig.class, WebSecurityConfig.class})
public class ActionsOnUserTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    PsqlUserDetailsService psqlUserDetailsService;

    @MockBean
    private UserService userService;

    @MockBean
    private RoleService roleService;

    /**
     * Test viewAddUser.
     * @throws Exception exception.
     */
    @Test
    public void whenAddUserThenPageAddUser() throws Exception {
        this.mvc.perform(
                get("/addUser").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("addUser")
        );
    }

    /**
     * Test addUser.
     * @throws Exception exception.
     */
    @Test
    public void whenAddUserThenSaveUserAndReturnLoginPage() throws Exception {
        User user = new User("user", "password");
        Optional<User> optional = Optional.empty();
        Role role = new Role();
        role.setName("ROLE_USER");
        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
        given(
                this.userService.findByLogin("user")
        ).willReturn(optional);
        given(
                this.roleService.findByName("ROLE_USER")
        ).willReturn(role);
        this.mvc.perform(
                post("/addUser").flashAttr("user", user).param("_csrf", csrfToken.getToken())
                .sessionAttr("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN", csrfToken)
        ).andExpect(
                view().name("login")
        ).andExpect(
                status().isOk()
        );
        verify(this.userService, times(1)).saveUser(user);
    }

    /**
     * Test addUser.
     * @throws Exception exception.
     */
    @Test
    public void whenAddUserSameLoginThenReturnPageAddUser() throws Exception {
        User user = new User("user", "password");
        Optional<User> optional = Optional.of(user);
        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
        given(
                this.userService.findByLogin("user")
        ).willReturn(optional);
        this.mvc.perform(
                post("/addUser").flashAttr("user", user).param("_csrf", csrfToken.getToken())
                        .sessionAttr("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN", csrfToken)
        ).andExpect(
                view().name("addUser")
        ).andExpect(
                status().isOk()
        ).andExpect(
                model().attribute("userExist", "Пользователь с таким логином уже существует")
        );
    }

    /**
     * Test deleteUser.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER", "ADMIN"})
    public void whenAdminDeleteUserThenDeleteUserAndReturnPageDeleteUser() throws Exception {
        User user = new User("Valera", "password");
        Optional<User> optional = Optional.of(user);
        given(
                this.userService.findByLogin("Valera")
        ).willReturn(optional);
        this.mvc.perform(
                get("/deleteUser?userLogin=Valera")
        ).andExpect(
                view().name("deleteUser")
        ).andExpect(
                status().isOk()
        );
        verify(this.userService, times(1)).deleteUser(user);
    }

    /**
     * Test deleteUser.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenUserDeleteUserThenDoNotDeleteUserAndReturnPageDeleteUser() throws Exception {
        this.mvc.perform(
                get("/deleteUser?userLogin=Valera")
        ).andExpect(
                view().name("deleteUser")
        ).andExpect(
                status().isOk()
        ).andExpect(
                model().attribute("notAdmin","нет прав для удаления")
        );
    }
}
