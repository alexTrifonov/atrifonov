package ru.job4j.carstore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;

/**
 * Store for car.
 * @author atrifonov.
 * @version 1.
 * @since 09.03.2018.
 */
public enum  CarStore {
    INSTANSE;
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();
    public List<Car> getCars(boolean withPhoto, MakeCar makeCar, Body body) {
        List<Car> list = new LinkedList<>();
        try (Session session = factory.openSession();) {
            session.beginTransaction();
            Query query;
            if (withPhoto) {
                query = session.createQuery("from Car where nameImg !='' AND make_car_id =:makeCarId AND body_id =:bodyId");
            } else {
                query = session.createQuery("from Car where make_car_id =:makeCarId AND body_id =:bodyId");
            }
            query.setParameter("makeCarId", makeCar.getId());
            query.setParameter("bodyId", body.getId());
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
                query = session.createQuery("from Car where nameImg !='' AND make_car_id =:makeCarId");
            } else {
                query = session.createQuery("from Car where make_car_id =:makeCarId");
            }
            query.setParameter("makeCarId", makeCar.getId());
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
                query = session.createQuery("from Car where nameImg !='' AND body_id=:bodyId");
            } else {
                query = session.createQuery("from Car where body_id=:bodyId");
            }
            query.setParameter("bodyId", body.getId());
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
                query = session.createQuery("from Car where nameImg !=''");
            } else {
                query = session.createQuery("from Car");
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
