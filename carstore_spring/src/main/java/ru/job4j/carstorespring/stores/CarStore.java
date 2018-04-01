package ru.job4j.carstorespring.stores;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;
import ru.job4j.carstorespring.models.*;

/**
 * Store for car.
 * @author atrifonov.
 * @version 1.
 * @since 09.03.2018.
 */
public enum  CarStore {
    INSTANSE;
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public Car add(Car car) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(car);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }

    public List<Car> getCars(boolean withPhoto, MakeCar makeCar, Body body) {
        List<Car> list = new LinkedList<>();
        try (Session session = factory.openSession();) {
            session.beginTransaction();
            Query query;
            if (withPhoto) {
                query = session.createQuery("from Car as c join fetch c.makeCar join fetch c.autoModel join fetch c.body " +
                        "join fetch c.transmission join fetch c.engine join fetch c.drive join fetch c.seller where c.nameImg !='' AND c.makeCar =:makeCar AND c.body =:body");
            } else {
                query = session.createQuery("from Car as c join fetch c.makeCar join fetch c.autoModel join fetch c.body " +
                        "join fetch c.transmission join fetch c.engine join fetch c.drive join fetch c.seller where c.makeCar =:makeCar AND c.body =:body");
            }
            query.setParameter("makeCar", makeCar);
            query.setParameter("body", body);
            list.addAll(query.list());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Car> getCars(boolean withPhoto, MakeCar makeCar) {
        List<Car> list = new LinkedList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query;
            if (withPhoto) {
                query = session.createQuery("from Car as c join fetch c.makeCar join fetch c.autoModel join fetch c.body " +
                        "join fetch c.transmission join fetch c.engine join fetch c.drive join fetch c.seller where c.nameImg !='' AND c.makeCar =:makeCar");
            } else {
                query = session.createQuery("from Car as c join fetch c.makeCar join fetch c.autoModel join fetch c.body " +
                        "join fetch c.transmission join fetch c.engine join fetch c.drive join fetch c.seller where c.makeCar =:makeCar");
            }
            query.setParameter("makeCar", makeCar);
            list.addAll(query.list());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Car> getCars(boolean withPhoto, Body body) {
        List<Car> list = new LinkedList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query;
            if (withPhoto) {
                query = session.createQuery("from Car as c join fetch c.makeCar join fetch c.autoModel join fetch c.body " +
                        "join fetch c.transmission join fetch c.engine join fetch c.drive join fetch c.seller where c.nameImg !='' AND c.body=:body");
            } else {
                query = session.createQuery("from Car as c join fetch c.makeCar join fetch c.autoModel join fetch c.body " +
                        "join fetch c.transmission join fetch c.engine join fetch c.drive join fetch c.seller where c.body=:body");
            }
            query.setParameter("body", body);
            list.addAll(query.list());
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Car> getCars(boolean withPhoto) {
        List<Car> list = new LinkedList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query;
            if (withPhoto) {
                query = session.createQuery("from Car as c join fetch c.makeCar join fetch c.autoModel join fetch c.body " +
                        "join fetch c.transmission join fetch c.engine join fetch c.drive join fetch c.seller where c.nameImg !=''");
            } else {
                query = session.createQuery("from Car as c join fetch c.makeCar join fetch c.autoModel join fetch c.body " +
                        "join fetch c.transmission join fetch c.engine join fetch c.drive join fetch c.seller");
            }
            list.addAll(query.list());
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
