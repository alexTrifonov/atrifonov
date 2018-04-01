package ru.job4j.carstorespring.stores;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.carstorespring.models.AutoModel;
import ru.job4j.carstorespring.models.Body;
import ru.job4j.carstorespring.models.Car;

import java.util.LinkedList;
import java.util.List;

/**
 * Store for body of car.
 * @author atrifonov.
 * @version 1.
 * @since 09.03.2018.
 */
public enum BodyStore {
    INSTANCE;
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public Body get(String bodyType) {
        Body body = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Body where bodyType =:bodyType");
            query.setParameter("bodyType", bodyType);
            List<Body> bodies = query.list();
            if (bodies.size() > 0) {
                body = bodies.iterator().next();
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }


    public Body add(Body body) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(body);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }

    public List<Body> getBodies() {
        List<Body> list = new LinkedList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Body");
            list.addAll(query.list());
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
