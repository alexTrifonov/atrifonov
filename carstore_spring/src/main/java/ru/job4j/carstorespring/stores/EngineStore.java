package ru.job4j.carstorespring.stores;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.carstorespring.models.Engine;

import java.util.LinkedList;
import java.util.List;

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

    public Engine get(String engineType) {
        Engine engine = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Engine where engineType =:engineType");
            query.setParameter("engineType", engineType);
            List<Engine> engines = query.list();
            if (engines.size() > 0) {
                engine = engines.iterator().next();
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return engine;
    }

    public List<Engine> getEngines() {
        List<Engine> engines = new LinkedList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Engine");
            engines = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return engines;
    }
}
