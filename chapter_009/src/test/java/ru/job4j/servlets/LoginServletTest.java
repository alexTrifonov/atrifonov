package ru.job4j.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static org.mockito.Mockito.*;

/**
 * Test.
 * @author atrifonov.
 * @since 13.01.2018.
 * @version 1
 */
public class LoginServletTest {
    /**
     * Instance of UserStore.
     */
    private final UserStore userStore = UserStore.INSTANCE;
    /**
     * Instance of RoleStore.
     */
    private final RoleStore roleStore = RoleStore.INSTANCE;

    /**
     * Test doPost.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Test()
    public void whenLoginAndPasswordExistsThenSessionSetAttributeLogin() throws ServletException, IOException {
        /*
        roleStore.add(new Role("admin", true, true, true, true, true));
        userStore.add(new User("Vasja", "vasja", "mail", "admin", LocalDateTime.of(2018, Month.JANUARY, 15, 19, 24, 30), "password", "Russia", "Yekaterinburg"));
        LoginServlet loginServlet = new LoginServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        String login = "vasja";
        String password = "password";

        doThrow(new IllegalStateException()).when(session).setAttribute("login", login);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        loginServlet.doPost(request, response);
        */
    }

    /**
     * Test doPost.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Test()
    public void whenLoginAndPasswordExistsThenSessionSetAttributeRole() throws ServletException, IOException {
        /*
        roleStore.add(new Role("user", true, false, false, false, false));
        userStore.add(new User("John", "jo", "mail", "user", LocalDateTime.of(2018, Month.JANUARY, 15, 19, 33, 35), "pass", "Russia", "Yekaterinburg"));

        LoginServlet loginServlet = new LoginServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        String login = "jo";
        String password = "pass";
        String role = "user";

        doThrow(new IllegalStateException()).when(session).setAttribute("role", role);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        loginServlet.doPost(request, response);
        */
    }

    /**
     * Test doPost.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Test(expected = IllegalStateException.class)
    public void whenLoginAndPasswordNotExistsThenGetError() throws ServletException, IOException {
        LoginServlet loginServlet = new LoginServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        String login = "met";
        String password = "passanger";
        String message = "Credential invalid";

        doThrow(new IllegalStateException()).when(request).setAttribute("error", message);

        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);

        loginServlet.doPost(request, response);

    }

    /**
     * Test doGet.
     * @throws ServletException exception.
     * @throws IOException exception.
     */
    @Test(expected = IllegalStateException.class)
    public void whenRequestHasParameterExistTrueThenSessionInvalidate() throws ServletException, IOException {
        LoginServlet loginServlet = new LoginServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);

        String exit = "true";

        doThrow(new IllegalStateException()).when(session).invalidate();

        when(request.getParameter("exit")).thenReturn(exit);
        when(request.getSession()).thenReturn(session);

        loginServlet.doGet(request, response);
    }
}
