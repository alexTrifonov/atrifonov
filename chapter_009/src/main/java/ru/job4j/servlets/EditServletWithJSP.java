package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for editing user.
 * @author atrifonov.
 * @version 1.
 * @since 04.01.2018.
 */
public class EditServletWithJSP extends HttpServlet {
    /**
     * Instance of UserStore.
     */
    private final UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", UserStore.INSTANCE.getUser(Integer.parseInt(req.getParameter("id"))));
        req.setAttribute("formatter", DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss"));
        req.getRequestDispatcher("/WEB-INF/views/editUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String dateString = req.getParameter("createDate");
        LocalDateTime createDate = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss"));
        User user = new User(name, login, email, createDate);
        Integer id = Integer.parseInt(req.getParameter("id"));
        user.setId(id);
        userStore.update(user);
        resp.sendRedirect(String.format("%s/startWithJSP", req.getContextPath()));
    }
}
