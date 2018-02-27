package ru.job4j.todolist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Add task to database.
 * @author atrifonov.
 * @version 1.
 * @since 27.02.2018.
 */
public class AddTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        Task task = new Task();
        task.setDescription(req.getParameter("description"));
        task.setCreated(new Timestamp(System.currentTimeMillis()));
        task.setDone(false);
        session.save(task);
        session.getTransaction().commit();
        session.close();
        factory.close();

        resp.sendRedirect(String.format("%s/start", req.getContextPath()));
    }
}
