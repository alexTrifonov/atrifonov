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
 * Store for make of car.
 * @author atrifonov.
 * @version 1.
 * @since 06.03.2018.
 */
public enum MakeStore {
    INSTANCE;
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public MakeCar add(MakeCar makeCar) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(makeCar);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeCar;
    }

    public MakeCar getMakeCar(String make) {
        MakeCar makeCar = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from MakeCar where make =:make");
            query.setParameter("make", make);
            List<MakeCar> makeCars = query.list();
            if (makeCars.size() > 0) {
                makeCar = makeCars.iterator().next();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeCar;
    }

    public List<MakeCar> getMakes() {
        List<MakeCar> makeCarList = new LinkedList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from MakeCar");
            makeCarList = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeCarList;
    }
}
