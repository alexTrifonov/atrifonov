package ru.job4j.fan;

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
 * Controller for start page.
 * @author atrifonov.
 * @version 1.
 * @since 01.02.2018.
 */
public class StartPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = null;
        HttpSession session = req.getSession();
        if (session.getAttribute("role").equals("admin")) {
            userList = UserStore.INSTANCE.getUsers(true);
        } else if (session.getAttribute("role").equals("moderator")) {
            userList = UserStore.INSTANCE.getUsers(false);
            userList.add(UserStore.INSTANCE.getUserById((Integer) session.getAttribute("id")));
        } else {
            userList = new LinkedList<>();
            int id = (Integer) session.getAttribute("id");
            userList.add(UserStore.INSTANCE.getUserById(id));
        }
        req.setAttribute("users", userList);
        req.getRequestDispatcher("/WEB-INF/views/start.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
