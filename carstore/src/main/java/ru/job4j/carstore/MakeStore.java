package ru.job4j.carstore;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Store for make of car.
 * @author atrifonov.
 * @version 1.
 * @since 06.03.2018.
 */
public enum MakeStore {
    INSTANCE;
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();


    public MakeCar getMakeCar(String make) {
        MakeCar makeCar = null;
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("from MakeCar where make =:make");
            query.setParameter("make", make);
            List<MakeCar> makeCars = query.list();
            if (makeCars.size() > 0) {
                makeCar = makeCars.iterator().next();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeCar;
    }

    public List<MakeCar> getMakes(){
        List<MakeCar> list = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement selectAllMakes = conn.prepareStatement("SELECT * FROM make_cars")) {
            ResultSet setMakes = selectAllMakes.executeQuery();
            while (setMakes.next()) {
                int id = setMakes.getInt(1);
                String make = setMakes.getString("make");
                MakeCar makeCar = new MakeCar(make);
                makeCar.setId(id);
                list.add(makeCar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public MakeCar getMake(String makeCar) {
        MakeCar make = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement selectMake = conn.prepareStatement("SELECT * FROM make_cars WHERE make = ?")) {
            selectMake.setString(1, makeCar);
            ResultSet resultSet = selectMake.executeQuery();
            if (resultSet.next()) {
                make = new MakeCar(makeCar);
                make.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return make;
    }
}
