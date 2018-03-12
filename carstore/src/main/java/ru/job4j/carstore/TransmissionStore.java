package ru.job4j.carstore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Store for Transmission.
 * @author atrifonov.
 * @version 1.
 * @since 12.03.2018.
 */
public enum  TransmissionStore {
    INSTANCE;
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public Transmission add(Transmission transmission) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(transmission);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transmission;
    }
}
