package ru.job4j.carstorespring.stores;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.carstorespring.models.Transmission;

import java.util.LinkedList;
import java.util.List;

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

    public Transmission get(String transmType) {
        Transmission transm = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Transmission where transmType =:transmType");
            query.setParameter("transmType", transmType);
            List<Transmission> transmissions = query.list();
            if (transmissions.size() > 0) {
                transm = transmissions.iterator().next();
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return transm;
    }

    public List<Transmission> getTransmissions() {
        List<Transmission> transmissions = new LinkedList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Transmission");
            transmissions = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transmissions;
    }
}
