package ru.job4j.carstore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Store for Drive.
 * @author atrifonov.
 * @version 1.
 * @since 12.03.2018.
 */
public enum  DriveStore {
    INSTANCE;
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public Drive add(Drive drive) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(drive);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drive;
    }
}
