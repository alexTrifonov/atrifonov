package ru.job4j.todolist;

import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/**
 * Class for filling table.
 * @author atrifonov.
 * @version 1.
 * @since 27.02.2018.
 */
public class FillTable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        List<Task> list = session.createQuery("from Task").list();
        boolean viewAll = Boolean.parseBoolean(req.getParameter("allTasks"));
        if(!viewAll) {
            list.removeIf(x -> x.isDone());

        }
        session.getTransaction().commit();
        session.close();
        factory.close();
        ObjectMapper mapper = new ObjectMapper();
        writer.append("[");
        StringBuilder sb = new StringBuilder();
        if (list.size() > 0) {
            Iterator<Task> iterator = list.iterator();
            sb.append(mapper.writeValueAsString(iterator.next()));
            while (iterator.hasNext()) {
                sb.append(",");
                sb.append(mapper.writeValueAsString(iterator.next()));
            }
        }
        writer.append(sb.toString());
        writer.append("]");
        writer.flush();
    }
}
