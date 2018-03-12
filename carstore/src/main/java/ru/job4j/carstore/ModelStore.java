package ru.job4j.carstore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Store for model of car.
 * @author atrifonov.
 * @version 1.
 * @since 06.03.2018.
 */
public enum ModelStore {
    INSTANCE;
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public AutoModel add(AutoModel model) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(model);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }

    public List<AutoModel> getModels(MakeCar makeCar) {
        List<AutoModel> list = new LinkedList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from AutoModel as a join fetch a.makeCar where a.makeCar =:makeCar");
            query.setParameter("makeCar", makeCar);
            list = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
