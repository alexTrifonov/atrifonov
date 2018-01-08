package ru.job4j.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

/**
 * Class for start page of application.
 * @author atrifonov.
 * @version 1.
 * @since 04.01.2018.
 */
public class StartPageServletWithJSP extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", UserStore.INSTANCE.getUsers());
        req.setAttribute("formatter", DateTimeFormatter.ofPattern("dd MM yyyy, HH:mm:ss"));
        req.getRequestDispatcher("/WEB-INF/views/start.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
