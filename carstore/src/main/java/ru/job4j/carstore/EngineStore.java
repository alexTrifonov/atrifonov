package ru.job4j.carstore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Store for Engine.
 * @author atrifonov.
 * @version 1.
 * @since 12.03.2018.
 */
public enum  EngineStore {
    INSTANCE;
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public Engine add(Engine engine) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(engine);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return engine;
    }
}
