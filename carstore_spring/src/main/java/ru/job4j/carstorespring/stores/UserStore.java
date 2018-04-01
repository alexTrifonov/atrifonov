package ru.job4j.carstorespring.stores;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.carstorespring.models.User;

import java.util.List;

/**
 * Class for store User entity in database.
 * @author atrifonov.
 * @version 1.
 * @since 01.04.2018.
 */
public enum UserStore {
    /**
     * Instance of UserStore.
     */
    INSTANCE;
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    /**
     * Construct UserStore.
     */
    UserStore() {
    }

    public User add(User user) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Get user from database with assigned login.
     * @param login login of user.
     * @return user.
     */
    public User getUser(String login) {
        User user = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from User as u where u.login =:login");
            query.setParameter("login", login);
            List<User> list = query.list();
            user = list.iterator().next();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Check exist user with login and password in database.
     * @param login login of user.
     * @param password password of user.
     * @return true if user exists.
     */
    public boolean isCredential(String login, String password) {
        boolean exists = false;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from User as u where u.login =:login AND u.password =:password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            List<User> list = query.list();
            if (list.size() > 0) {
                exists = true;
            }
            session.getTransaction().commit();
        }
        return exists;
    }
}