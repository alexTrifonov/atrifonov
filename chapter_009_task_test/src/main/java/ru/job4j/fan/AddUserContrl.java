package ru.job4j.fan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;


/**
 * Controller for adding user.
 * @author atrifonov.
 * @version 1.
 * @since 02.02.2018.
 */
public class AddUserContrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", RoleStore.INSTANCE.getRoles());
        req.setAttribute("musics", MusicStore.INSTANCE.getMusicTypes());
        req.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String roleName = req.getParameter("role_name");
        String[] musicNames = req.getParameterValues("music_name[]");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        int houseNumber;
        int flatNumber;
        try {
            houseNumber = Integer.parseInt(req.getParameter("houseNumber"));
            flatNumber = Integer.parseInt(req.getParameter("flatNumber"));
        } catch (NumberFormatException e) {
            houseNumber = 0;
            flatNumber = 0;

        }
        if (houseNumber == 0 || flatNumber == 0) {
            req.setAttribute("roles", RoleStore.INSTANCE.getRoles());
            req.getRequestDispatcher("/WEB-INF/views/addUser.jsp").forward(req, resp);
        }


        String password = req.getParameter("password");


        if (!(name.equals("") || login.equals("") || roleName.equals("") || country.equals("") || city.equals("")
                || street.equals("") || password.equals("") || musicNames.length == 0)) {
            Address address = new Address(country, city, street, houseNumber, flatNumber);
            Role role = RoleStore.INSTANCE.getRoleByName(roleName);
            User user = new User(name, login, role, address, password);
            UserStore.INSTANCE.create(user);
            for (String x : musicNames) {
                MusicType musicType = MusicStore.INSTANCE.getMusicTypeByType(x);
                UserMusicStore.INSTANCE.createUserMusic(user, musicType);
            }

        }

        resp.sendRedirect(String.format("%s/start", req.getContextPath()));
    }
}
