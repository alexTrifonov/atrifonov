package ru.job4j.servlets;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * Class for control of users.
 * @author atrifonov.
 * @since 23.12.2017.
 * @version 1.
 */
public class UsersServlet extends HttpServlet {
    /**
     * Store for users.
     */
    private final UserStore userStore = UserStore.INSTANCE;
    /**
     * Logger.
     */
    private static final Logger USERS_SERVLET = LogManager.getLogger(UsersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = new PrintWriter(resp.getOutputStream());
        List<User> userList = this.userStore.getUsers();
        USERS_SERVLET.info(String.format("userList.size() = %d", userList.size()));
        printWriter.append(String.format("user list:%n"));
        printWriter.flush();
        for (User x : userList) {
            printWriter.append(String.format("id = %d, name = %s, login = %s, email = %s, createDate = %s  %n", x.getId(), x.getName(), x.getLogin(),
                    x.getEmail(), x.getCreateDate().format(DateTimeFormatter.ofPattern("dd MMM yy, HH:mm:ss", new Locale("en")))));
            printWriter.flush();

        }
        USERS_SERVLET.info("doGet executed");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String roleName = req.getParameter("roleName");
        String dateString = req.getParameter("createDate");
        LocalDateTime createDate = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss"));
        String password = req.getParameter("password");
        User user = new User(name, login, email, roleName, createDate, password, null, null);
        this.userStore.add(user);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String roleName = req.getParameter("roleName");
        String dateString = req.getParameter("createDate");
        LocalDateTime createDate = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss"));
        String password = req.getParameter("password");
        User user = new User(name, login, email, roleName, createDate, password, null, null);
        user.setId(id);
        this.userStore.update(user);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);
        this.userStore.delete(id);
    }
}
