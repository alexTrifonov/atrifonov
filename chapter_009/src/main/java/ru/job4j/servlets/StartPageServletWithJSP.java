package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for start page of application.
 * @author atrifonov.
 * @version 1.
 * @since 04.01.2018.
 */
public class StartPageServletWithJSP extends HttpServlet {
    /**
     * Instance of RoleStore.
     */
    private final RoleStore roleStore = RoleStore.INSTANCE;
    /**
     * Instance of UserStore.
     */
    private final UserStore userStore = UserStore.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = null;
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        if (session.getAttribute("role").equals("admin")) {
            userList = userStore.getUsers();
        } else {
            userList = new LinkedList<>();
            userList.add(userStore.getUser(login));
        }
        req.setAttribute("users", userList);
        req.setAttribute("roles", roleStore.getRoles());
        req.setAttribute("formatter", DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss"));
        req.getRequestDispatcher("/WEB-INF/views/start.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
