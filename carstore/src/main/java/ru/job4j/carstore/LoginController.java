package ru.job4j.carstore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 *  For log in.
 *  @author atrifonov.
 *  @since 06.03.2018.
 *  @version 1.
 */
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String exit = req.getParameter("exit");
        if (exit != null && exit.equals("true")) {
            HttpSession session = req.getSession();
            session.invalidate();
        }
        req.getRequestDispatcher("/WEB-INF/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean isCredential = UserStore.INSTANCE.isCredential(login, password);

        if (isCredential) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            User user = UserStore.INSTANCE.getUser(login);
            session.setAttribute("id", user.getId());
            resp.sendRedirect(String.format("%s/start", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credential invalid");
            doGet(req, resp);
        }
    }
}
