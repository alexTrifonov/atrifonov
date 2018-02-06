package ru.job4j.fan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for deleting user from database.
 * @author atrifonov.
 * @version 1.
 * @since 06.02.2018.
 */
public class DeleteUserContrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User user = UserStore.INSTANCE.getUserById(Integer.parseInt(id));
        UserMusicStore.INSTANCE.deleteAllUserMusic(user);
        UserStore.INSTANCE.delete(user.getId());
        resp.sendRedirect(String.format("%s/start", req.getContextPath()));
    }
}
