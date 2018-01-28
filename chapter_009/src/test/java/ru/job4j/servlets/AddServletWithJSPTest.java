package ru.job4j.servlets;

import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author atrifonov.
 * @version 1.
 * @since 15.01.2018.
 */
public class AddServletWithJSPTest {
    /**
     * Test doGet.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Test(expected = IllegalStateException.class)
    public void whenExecuteDoGetThenSessionSetAttributeRoles() throws ServletException, IOException {
        AddServletWithJSP addServletWithJSP = new AddServletWithJSP();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher("/WEB-INF/views/addUser.jsp")).thenReturn(requestDispatcher);

        doThrow(new IllegalStateException()).when(requestDispatcher).forward(request, response);

        addServletWithJSP.doGet(request, response);
    }

    /**
     * Test doPost.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Test
    public void whenRequestGetAllParametersUserThenUserAddToDatabase() throws ServletException, IOException {
        AddServletWithJSP addServletWithJSP = new AddServletWithJSP();
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        RoleStore.INSTANCE.add(new Role("user", true, false, false, false, false));

        String name = "Evgen";
        String login = "gen";
        String email = "email";
        String roleName = "user";
        String createDate = "15 01 2018, 20:48:23";
        String password = "password";
        String country = "Russia";
        String city = "Yekaterinburg";

        when(request.getParameter("name")).thenReturn(name);
        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("role_name")).thenReturn(roleName);
        when(request.getParameter("createDate")).thenReturn(createDate);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("confirmPassword")).thenReturn(password);
        when(request.getParameter("country")).thenReturn(country);
        when(request.getParameter("city")).thenReturn(city);

        addServletWithJSP.doPost(request, response);

        User addedUser = UserStore.INSTANCE.getUser(login);

        boolean equal = addedUser.getName().equals(name) && addedUser.getEmail().equals(email)
                && addedUser.getRoleName().equals(roleName)
                && addedUser.getCreateDate().format(DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss")).equals(createDate)
                && addedUser.getPassword().equals(password);

        assertThat(equal, is(true));
    }
}
