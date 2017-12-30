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
 * @since 30.12.2017.
 */
public class AddServlet extends HttpServlet {
    /**
     * Instance of UserStore.
     */
    private final UserStore userStore = UserStore.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        StringBuilder sb = new StringBuilder();
        sb.append("<form action='").append(req.getContextPath()).append("/add' method='post'>");
        sb.append("Name : <input type='text' name='name'/>");
        sb.append("login : <input type='text' name='login'/>");
        sb.append("email : <input type='text' name='email'/>");
        sb.append("createDate : <input type='text' name='createDate' />");
        sb.append("<input type='submit' value='add new user' />");
        sb.append("</form>");

        writer.append("<!DOCTYPE html>\n");
        writer.append("<html lang=\"en\">\n");
        writer.append("<head>\n");
        writer.append("    <meta charset=\"UTF-8\">\n");
        writer.append("    <title>Title</title>\n");
        writer.append("</head>\n");
        writer.append("<body>\n");
        writer.append(sb.toString());
        writer.append("</body>\n");
        writer.append("</html>");


        writer.flush();
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
        resp.sendRedirect((req.getContextPath() + "/start"));
    }
}
