package ru.job4j.servlets;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.Mockito.mock;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Test.
 * @author atrifonov.
 * @version 1.
 * @since 15.01.2018.
 */
public class DeleteServletTest {
    /**
     * Test doGet.
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void whenRequestHasTrueIdThenUserToDelete() throws ServletException, IOException {
        DeleteServlet deleteServlet = new DeleteServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        User user = new User("Evgesha", "gesha", "mail", "user",
                LocalDateTime.of(2018, Month.JANUARY, 15, 21, 0, 30),"password");
        user = UserStore.INSTANCE.add(user);
        String id = String.format("%d", user.getId());

        when(request.getParameter("id")).thenReturn(id);

        deleteServlet.doGet(request, response);

        boolean deleted = user.getId() != 0 && UserStore.INSTANCE.getUser(id) == null;
        assertThat(deleted, is(true));
    }
}
