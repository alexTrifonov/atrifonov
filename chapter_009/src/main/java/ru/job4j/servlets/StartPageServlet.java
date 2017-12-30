package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Class for start page of application.
 * @author atrifonov.
 * @version 1.
 * @since 26.12.2017.
 */
public class StartPageServlet extends HttpServlet {
    /**
     * Instance of UserStore.
     */
    private final UserStore userStore = UserStore.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;UTF-8");
        List<User> userList = userStore.getUsers();
        StringBuilder sb = new StringBuilder("<table>");
        sb.append("<tr><td>Name</td><td>login</td><td>email</td><td>date of creation</td>");
        for (User user : userList) {
            sb.append("<tr>");
            sb.append("<td>").append(user.getName()).append("</td>");
            sb.append("<td>").append(user.getLogin()).append("</td>");
            sb.append("<td>").append(user.getEmail()).append("</td>");
            sb.append("<td>").append(user.getCreateDate().format(DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss"))).append("</td>");

            sb.append("<td>");
            sb.append("<a href='").append(req.getContextPath()).append("/edit?").append("id=").append(user.getId()).append("'a>edit</a>");
            sb.append("</td>");
            sb.append("<td>");
            sb.append("<a href='").append(req.getContextPath()).append("/delete?").append("id=").append(user.getId()).append("'>delete</a>");
            sb.append("</td></tr>");
        }
        sb.append("</table>");
        sb.append("<form action='").append(req.getContextPath()).append("/add'").append("method='get'>");
        sb.append("<input type ='submit' value='add new user'/>");
        sb.append("</form>");

        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html>");
        writer.append("<html lang='en'>");
        writer.append("<head>");
        writer.append("    <meta charset='UTF-8'/>");
        writer.append("    <title>Title</title>");
        writer.append("</head>");
        writer.append("<body>");
        writer.append(sb.toString());
        writer.append("</body>");
        writer.append("</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
