package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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
        resp.setContentType("text/html");
        System.out.println("id=" + req.getParameter("id"));
        req.getSession().setAttribute("id", req.getParameter("id"));
        resp.sendRedirect(String.format("%s/editUser.jsp", req.getContextPath()));
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
        resp.sendRedirect(String.format("%s/start.jsp", req.getContextPath()));
    }
}
