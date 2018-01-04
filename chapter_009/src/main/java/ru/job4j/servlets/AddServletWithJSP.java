package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for adding user to store.
 * @author atrifonov.
 * @version 1.
 * @since 04.01.2018.
 */
public class AddServletWithJSP extends HttpServlet {
    /**
     * Instance of UserStore.
     */
    private final UserStore userStore = UserStore.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.sendRedirect(String.format("%s/addUser.jsp", req.getContextPath()));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String dateString = req.getParameter("createDate");
        LocalDateTime createDate = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss"));
        User user = new User(name, login, email, createDate);
        userStore.add(user);
        resp.sendRedirect(String.format("%s/start.jsp", req.getContextPath()));
    }
}
