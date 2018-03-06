package ru.job4j.carstore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Properties;

/**
 * Class for store User entity in database.
 * @author atrifonov.
 * @version 1.
 * @since 30.01.2018.
 */
public enum UserStore {
    /**
     * Instance of UserStore.
     */
    INSTANCE;


    /**
     * Construct UserStore.
     */
    UserStore() {
    }

    /**
     * Get user from database with assigned login.
     * @param login login of user.
     * @return user.
     */
    public User getUser(String login) {
        User user = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getUserFromDB = conn.prepareStatement("SELECT * FROM sellers WHERE login = ?")) {

            getUserFromDB.setString(1, login);
            ResultSet resultSet = getUserFromDB.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"));
            }
        } catch (SQLException e) {
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
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement prepStm = conn.prepareStatement("SELECT * FROM sellers WHERE login = ? AND password = ?;")) {
            prepStm.setString(1, login);
            prepStm.setString(2, password);
            ResultSet resultSet = prepStm.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
}

