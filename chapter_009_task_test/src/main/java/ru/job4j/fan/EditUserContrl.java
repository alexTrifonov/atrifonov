package ru.job4j.fan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * Controller for editing user.
 * @author atrifonov.
 * @version 1.
 * @since 05.02.2018
 */
public class EditUserContrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = UserStore.INSTANCE.getUserById(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("user", user);
        req.setAttribute("roles", RoleStore.INSTANCE.getRoles());
        List<MusicType> listMusic = MusicStore.INSTANCE.getMusicTypes();
        List<String> listType = new LinkedList<>();
        for(MusicType x : listMusic) {
            listType.add(x.getType());
        }
        List<MusicType> userMusic = user.getMusicTypeList();
        List<String> listUserType = new LinkedList<>();
        for(MusicType x : userMusic) {
            listUserType.add(x.getType());
        }
        listType.removeAll(listUserType);
        req.setAttribute("userMusics", listUserType);
        req.setAttribute("addMusics", listType);

        req.getRequestDispatcher("/WEB-INF/views/editUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String roleName = req.getParameter("role_name");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String houseNumber = req.getParameter("houseNumber");
        String flatNumber = req.getParameter("flatNumber");
        String password = req.getParameter("password");
        String idStr = req.getParameter("id");
        String lastLogin = req.getParameter("lastLogin");
        String lastRole = req.getParameter("lastRole");


        if (!name.equals("") && !login.equals("") && !roleName.equals("")
                && !country.equals("") && !city.equals("") && !street.equals("")
                && !houseNumber.equals("") && !flatNumber.equals("") && !password.equals("")
                && !lastLogin.equals("") && ! lastRole.equals("")) {

            Role role = RoleStore.INSTANCE.getRoleByName(roleName);
            Address address;
            try {
                address = new Address(country, city, street, Integer.parseInt(houseNumber), Integer.parseInt(flatNumber));
                User user = new User(name, login, role, address, password);
                user.setId(Integer.parseInt(idStr));
                UserStore.INSTANCE.update(user);

                String[] musicDel = req.getParameterValues("music_name_del[]");
                if (musicDel != null) {
                    for (String x :
                            musicDel) {
                        MusicType musicType = MusicStore.INSTANCE.getMusicTypeByType(x);
                        UserMusicStore.INSTANCE.deleteUserMusic(user, musicType);
                    }
                }

                String[] musicAdd = req.getParameterValues("music_name_add[]");
                if (musicAdd != null) {
                    for (String x:
                            musicAdd) {
                        MusicType musicType = MusicStore.INSTANCE.getMusicTypeByType(x);
                        UserMusicStore.INSTANCE.createUserMusic(user, musicType);
                    }
                }

                HttpSession session = req.getSession();
                String sessionLogin = (String) session.getAttribute("login");
                if (sessionLogin.equals(req.getParameter("lastLogin"))) {
                    if(!req.getParameter("lastLogin").equals(login)) {
                        session.setAttribute("login", login);
                    }

                    if (!req.getParameter("lastRole").equals(roleName)) {
                        session.setAttribute("role", roleName);
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect(String.format("%s/start", req.getContextPath()));
    }
}
