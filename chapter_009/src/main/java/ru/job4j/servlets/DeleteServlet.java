package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class for delete user from store.
 * @author atrifonov.
 * @version 1.
 * @since 30.12.2017.
 */
public class DeleteServlet extends HttpServlet {
    /**
     * Instance of UserStore.
     */
    private final UserStore userStore = UserStore.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        userStore.delete(Integer.parseInt(id));
        resp.sendRedirect(String.format("%s/startWithJSP", req.getContextPath()));
    }
}
