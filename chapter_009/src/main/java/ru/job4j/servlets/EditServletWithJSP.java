package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for editing user.
 * @author atrifonov.
 * @version 1.
 * @since 04.01.2018.
 */
public class EditServletWithJSP extends HttpServlet {
    /**
     * Instance of UserStore.
     */
    private final UserStore userStore = UserStore.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("user", UserStore.INSTANCE.getUser(Integer.parseInt(req.getParameter("id"))));
        User user = UserStore.INSTANCE.getUser(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("roles", RoleStore.INSTANCE.getRoles());
        req.setAttribute("userCountryName", user.getCountry());
        req.setAttribute("countries", CountryStore.INSTANCE.getCountries());
        req.setAttribute("userCityName", user.getCity());
        req.setAttribute("formatter", DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss"));
        req.getRequestDispatcher("/WEB-INF/views/editUser.jsp").forward(req, resp);
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
        String idStr = req.getParameter("id");
        String lastLogin = req.getParameter("lastLogin");
        String lastRole = req.getParameter("lastRole");


        if (!name.equals("") && !login.equals("") && !email.equals("") && !roleName.equals("") && !dateString.equals("")
                && !country.equals("") && !city.equals("") && !password.equals("") && !confirmPassword.equals("")
                && !lastLogin.equals("") && ! lastRole.equals("") && password.equals(confirmPassword)) {

            LocalDateTime createDate = LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss"));
            Integer id = Integer.parseInt(idStr);
            User user = new User(name, login, email, roleName, createDate, password, country, city);
            user.setId(id);
            userStore.update(user);

            HttpSession session = req.getSession();
            String currentLogin = (String) session.getAttribute("login");
            if (currentLogin.equals(req.getParameter("lastLogin"))) {
                if(!req.getParameter("lastLogin").equals(login)) {
                    session.setAttribute("login", login);
                }

                if (!req.getParameter("lastRole").equals(roleName)) {
                    session.setAttribute("role", roleName);
                }
            }
        }

        resp.sendRedirect(String.format("%s/startWithJSP", req.getContextPath()));
    }
}
