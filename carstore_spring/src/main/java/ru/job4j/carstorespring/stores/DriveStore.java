package ru.job4j.carstorespring.stores;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.carstorespring.models.Drive;
import ru.job4j.carstorespring.models.MakeCar;

import java.util.LinkedList;
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

    public Drive get(String driveType) {
        Drive drive = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Drive where driveType =:driveType");
            query.setParameter("driveType", driveType);
            List<Drive> drives = query.list();
            if (drives.size() > 0) {
                drive = drives.iterator().next();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drive;
    }

    public List<Drive> getDrives() {
        List<Drive> drives = new LinkedList<>();
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from Drive");
            drives = query.list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drives;
    }
}
