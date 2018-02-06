package ru.job4j.fan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Controller for check login and password of user.
 * @author atrifonov.
 * @version 1.
 * @since 01.02.2018.
 */
public class LoginController extends HttpServlet {

    /**
     * Instance of RoleStore.
     */
    private final RoleStore roleStore = RoleStore.INSTANCE;

    /**
     * Instance of AddressStore.
     */
    private final AddressStore addressStore = AddressStore.INSTANCE;

    /**
     * Instance of MusicStore.
     */
    private final MusicStore musicStore = MusicStore.INSTANCE;
    /**
     * Instance of UserStore.
     */
    private final UserStore userStore = UserStore.INSTANCE;
    /**
     * Instance of UserMusicStore.
     */
    private final UserMusicStore userMusicStore = UserMusicStore.INSTANCE;
    /**
     * Instance of UserStore.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String exit = req.getParameter("exit");
        if (exit != null && exit.equals("true")) {
            HttpSession session = req.getSession();
            session.invalidate();
        }

        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int id = userStore.availableId(login, password);
        if (id > 0) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            User user = userStore.getUserById(id);
            session.setAttribute("role", user.getRole().getName());
            req.setAttribute("id", id);
            session.setAttribute("id", id);
            resp.sendRedirect(String.format("%s/start", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credential invalid");
            doGet(req, resp);
        }
    }
}
