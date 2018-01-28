package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Class for entry point with login.
 * @author atrifonov.
 * @version 1.
 * @since 10.01.2017.
 */
public class LoginServlet extends HttpServlet {
    /**
     * Instance of RoleStore.
     */
    private final RoleStore roleStore = RoleStore.INSTANCE;

    /**
     * Instance of CountryStore.
     */
    private final CountryStore countryStore = CountryStore.INSTANCE;

    /**
     * Instance of CityStore.
     */
    private final CityStore cityStore = CityStore.INSTANCE;
    /**
     * Instance of UserStore.
     */
    private final UserStore userStore = UserStore.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String exit = req.getParameter("exit");
        if (exit != null && exit.equals("true")) {
            HttpSession session = req.getSession();
            session.invalidate();
        }

        req.getRequestDispatcher("/WEB-INF/views/loginView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (userStore.isCredential(login, password)) {
            HttpSession session = req.getSession();
            session.setAttribute("login", login);
            User user = userStore.getUser(login);
            session.setAttribute("role", user.getRoleName());
            resp.sendRedirect(String.format("%s/startWithJSP", req.getContextPath()));
        } else {
            req.setAttribute("error", "Credential invalid");
            doGet(req, resp);
        }
    }
}
