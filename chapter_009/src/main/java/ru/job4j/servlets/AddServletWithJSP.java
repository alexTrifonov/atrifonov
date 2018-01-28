package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for adding user to store.
 * @author atrifonov.
 * @version 1.
 * @since 04.01.2018.
 */
public class AddServletWithJSP extends HttpServlet {
    /**
     * Instance of UserStore.
     */
    private final UserStore userStore = UserStore.INSTANCE;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", RoleStore.INSTANCE.getRoles());
        req.setAttribute("countries", CountryStore.INSTANCE.getCountries());
        req.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String roleName = req.getParameter("role_name");
        String dateString = req.getParameter("createDate");

        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");

        if (!(name.equals("") || login.equals("") || email.equals("") || roleName.equals("") || dateString.equals("")
        || country.equals("") || city.equals("") || password.equals("") || confirmPassword.equals(""))) {
            if (!password.equals(confirmPassword)) {
                req.setAttribute("roles", RoleStore.INSTANCE.getRoles());
                req.setAttribute("countries", CountryStore.INSTANCE.getCountries());
                req.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(req, resp);
            }
            LocalDateTime createDate = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss"));
            User user = new User(name, login, email, roleName, createDate, password, country, city);
            userStore.add(user);
        }

        resp.sendRedirect(String.format("%s/startWithJSP", req.getContextPath()));
    }
}