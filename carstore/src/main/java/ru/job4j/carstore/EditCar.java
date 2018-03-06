package ru.job4j.carstore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

/**
 * Edit car.
 * @author atrifonov.
 * @since 06.03.2018.
 */
public class EditCar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idCar = req.getParameter("idCar");
        System.out.println(String.format("method GET id = %s", idCar));
        req.setAttribute("idCar", idCar);
        req.getRequestDispatcher("/WEB-INF/editCar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idCar = req.getParameter("idCar");
        String sold = req.getParameter("sold");
        System.out.println(String.format("method POST, id = %s, sold = %s", idCar, sold));

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

        Query query;
        query = session.createQuery("from Car where id = :id");
        query.setParameter("id", Integer.parseInt(idCar));
        List<Car> makeCars = query.list();
        Car carInBase = makeCars.iterator().next();

        carInBase.setStatus(Boolean.parseBoolean(sold));

        session.update(carInBase);
        session.getTransaction().commit();
        session.close();
        factory.close();
        resp.sendRedirect(String.format("%s/start", req.getContextPath()));
    }
}
