package ru.job4j.carstore;

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
 * Fill select engine.
 * @author atifonov.
 * @version 1.
 * @since 06.03.2018.
 */
public class EngineController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        List<Engine> list = session.createQuery("from Engine").list();
        session.getTransaction().commit();
        session.close();
        factory.close();
        ObjectMapper mapper = new ObjectMapper();
        writer.append("[");
        StringBuilder sb = new StringBuilder();
        if (list.size() > 0) {
            Iterator<Engine> iterator = list.iterator();
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
