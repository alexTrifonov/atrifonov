package ru.job4j.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test.
 * @author atrifonov.
 * @version 1.
 * @since 16.01.2018.
 */
public class EditServletWithJSPTest {
    /**
     * Test doPost.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Test
    public void whenRequestHasNewParameterOfUserThenUserUpdate() throws ServletException, IOException {
        EditServletWithJSP editServletWithJSP = new EditServletWithJSP();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        List<User> list = UserStore.INSTANCE.getUsers();
        User oldUser = list.iterator().next();

        int id = oldUser.getId();
        String idStr = "" + id;
        String oldName = oldUser.getName();
        String name = String.format("%s%s", oldName, "_new");

        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("login")).thenReturn(oldUser.getLogin());
        when(request.getParameter("email")).thenReturn(oldUser.getEmail());
        when(request.getParameter("role_name")).thenReturn(oldUser.getRoleName());
        when(request.getParameter("createDate")).thenReturn(oldUser.getCreateDate().format(DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss")));
        when(request.getParameter("password")).thenReturn(oldUser.getPassword());
        when(request.getParameter("id")).thenReturn(idStr);
        when(request.getParameter("lastLogin")).thenReturn(oldUser.getLogin());
        when(request.getParameter("lastRole")).thenReturn(oldUser.getRoleName());


        editServletWithJSP.doPost(request, response);

        assertThat(UserStore.INSTANCE.getUser(id).getName(), is(name));

        User newUser = new User(oldUser.getName(), oldUser.getLogin(), oldUser.getEmail(), oldUser.getRoleName(), oldUser.getCreateDate(),
                oldUser.getPassword());
        newUser.setId(id);
        UserStore.INSTANCE.update(newUser);
    }
}
