package ru.job4j.carstore;

import com.sun.deploy.net.HttpRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Fill table on start page.
 * @author atifonov.
 * @version 1.
 * @since 06.03.2018.
 */
public class TableController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        List<Car> list = session.createQuery("from Car").list();
        ObjectMapper mapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        if (list.size() > 0) {
            Iterator<Car> iterator = list.iterator();
            Car car = iterator.next();;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            mapper.writeValue(out, getMap(car, req));
            while (iterator.hasNext()) {
                car = iterator.next();
                out.write(", ".getBytes());
                mapper.writeValue(out, getMap(car, req));
            }
            sb.append(out.toString("UTF-8"));
        }
        session.getTransaction().commit();
        session.close();
        factory.close();
        sb.append("]");
        writer.append(sb.toString());
        writer.flush();
    }

    private Map<String, String> getMap(Car car, HttpServletRequest req) {
        Map<String, String> map = new HashMap<>();
        map.put("carStr", car.toString());
        map.put("nameImg", car.getNameImg());
        map.put("sold", Boolean.toString(car.isStatus()));
        if (car.getSeller().getLogin().equals(req.getSession().getAttribute("login"))) {
            map.put("author", Boolean.toString(true));
        } else {
            map.put("author", Boolean.toString(false));
        }
        map.put("idCar", String.valueOf(car.getId()));
        return map;
    }
}
