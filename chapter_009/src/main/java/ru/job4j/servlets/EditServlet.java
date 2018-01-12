package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Class for editing user.
 * @author atrifonov.
 * @version 1.
 * @since 30.12.2017.
 */
public class EditServlet extends HttpServlet {
    /**
     * Instance of UserStore.
     */
    private final UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String id = req.getParameter("id");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        User user = userStore.getUser(Integer.parseInt(id));
        StringBuilder sb = new StringBuilder();
        sb.append("<form action='").append(req.getContextPath()).append("/edit' method='post'>");
        sb.append("<input type='hidden' name='id' value='").append(id).append("'/>");
        sb.append("Name : <input type='text' name='name' value='").append(user.getName()).append("'/>");
        sb.append("login : <input type='text' name='login' value='").append(user.getLogin()).append("'/>");
        sb.append("email : <input type='text' name='email' value='").append(user.getEmail()).append("'/>");
        sb.append("createDate : <input type='text' name='createDate' value='").append(user.getCreateDate().format(DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss", new Locale("en")))).append("'/>");
        sb.append("<input type='submit' value='update'/>");
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
        String roleName = req.getParameter("roleName");
        String dateString = req.getParameter("createDate");
        LocalDateTime createDate = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss"));
        String password = req.getParameter("password");
        User user = new User(name, login, email, roleName, createDate, password);
        Integer id = Integer.parseInt(req.getParameter("id"));
        user.setId(id);
        userStore.update(user);
        System.out.println("req.getContextPath() = " + req.getContextPath());
        resp.sendRedirect((req.getContextPath() + "/start"));
    }
}
