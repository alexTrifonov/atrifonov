package ru.job4j.servlets;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Class for store of user in database.
 */
public class UserStore {
    /**
     * Instance of UserStore.
     */
    //private static final UserStore INSTANCE = new UserStore();
    private static volatile UserStore instance;
    /**
     * Mutex.
     */
    private static Object mutex = new Object();
    /**
     * Logger.
     */
    private static final Logger USER_STORE = LogManager.getLogger(UserStore.class);
    /**
     * Construct UserStore.
     */
    private UserStore() {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("CREATE TABLE if NOT EXISTS users_servlet(id SERIAL PRIMARY KEY, "
                     + "name VARCHAR(100), login VARCHAR(100), email VARCHAR (100), create_date TIMESTAMP);");) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get single INSTANCE of UserStore.
     * @return INSTANCE.
     */
    public static UserStore getInstance() {
        UserStore result = instance;
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    result = new UserStore();
                    instance = result;
                }
            }
        }
        return result;
    }

    /**
     * Add user to database.
     * @param user user for adding.
     * @return user with id.
     */
    public User add(User user) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement prepStm = conn.prepareStatement("INSERT INTO users_servlet (name, login, email, create_date) VALUES (?, ?, ?, ?) RETURNING id;");
             PreparedStatement prepStmSelect = conn.prepareStatement("SELECT * FROM users_servlet WHERE users_servlet.name = ? AND users_servlet.login = ? "
                     + "AND users_servlet.email = ? AND users_servlet.create_date = ?;")) {
            prepStmSelect.setString(1, user.getName());
            prepStmSelect.setString(2, user.getLogin());
            prepStmSelect.setString(3, user.getEmail());
            prepStmSelect.setTimestamp(4, Timestamp.valueOf(user.getCreateDate()));
            ResultSet resSet = prepStmSelect.executeQuery();

            if (!resSet.next()) {
                prepStm.setString(1, user.getName());
                prepStm.setString(2, user.getLogin());
                prepStm.setString(3, user.getEmail());
                prepStm.setTimestamp(4, Timestamp.valueOf(user.getCreateDate()));
                ResultSet resultSetInsert = prepStm.executeQuery();
                if (resultSetInsert.next()) {
                    int id = resultSetInsert.getInt(1);
                    user.setId(id);
                    USER_STORE.info(String.format("Insert user: id = %d, name = %s, login = %s, email = %s, create_date = %s%n",
                            id,
                            user.getName(),
                            user.getLogin(), user.getEmail(),
                            user.getCreateDate().format(DateTimeFormatter.ofPattern("dd MMM yy, HH:mm:ss", new Locale("en")))));
                }

            } else {
                USER_STORE.info("Method attempted insert some user.");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Update user in database.
     * @param newUser updated user.
     */
    public void update(User newUser) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement prepStm = conn.prepareStatement("UPDATE users_servlet set name = ?, login = ?, email = ?, create_date = ? WHERE id = ? ;")) {
            prepStm.setString(1, newUser.getName());
            prepStm.setString(2, newUser.getLogin());
            prepStm.setString(3, newUser.getEmail());
            prepStm.setTimestamp(4, Timestamp.valueOf(newUser.getCreateDate()));
            prepStm.setInt(5, newUser.getId());
            prepStm.executeUpdate();
            USER_STORE.info(String.format("Update user with id = %d: name = %s, login = %s, email = %s, create_date = %s%n", newUser.getId(),
                    newUser.getName(), newUser.getLogin(), newUser.getEmail(),
                    newUser.getCreateDate().format(DateTimeFormatter.ofPattern("dd MMM yy, HH:mm:ss", new Locale("en")))));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete user from database.
     * @param id id of deleting user.
     */
    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement prepStm = conn.prepareStatement("DELETE FROM users_servlet WHERE id = ?");
             PreparedStatement prepStmSelect = conn.prepareStatement("SELECT * FROM users_servlet WHERE  id = ?")) {
            prepStm.setInt(1, id);
            prepStmSelect.setInt(1, id);
            ResultSet resultSet = prepStmSelect.executeQuery();
            prepStm.executeUpdate();
            if (resultSet.next()) {
                USER_STORE.info(String.format("Delete user with id = %d: name = %s, login = %s, email = %s, create_date = %s%n",
                        resultSet.getInt(1), resultSet.getString("name"), resultSet.getString("login"), resultSet.getString("email"),
                        resultSet.getTimestamp("create_date")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get user from database with assigned id.
     * @param id id of user.
     * @return user.
     */
    public User getUser(int id) {
        User user = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement getUserFromDB = conn.prepareStatement("SELECT name, login, email, create_date FROM users_servlet WHERE id = ?")) {
            getUserFromDB.setInt(1, id);
            ResultSet resultSet = getUserFromDB.executeQuery();
            if (resultSet.next()) {
                LocalDateTime dateTime = resultSet.getTimestamp("create_date").toLocalDateTime();
                user = new User(resultSet.getString("name"), resultSet.getString("login"), resultSet.getString("email"), dateTime);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    /**
     * Get list of all users.
     * @return list of users.
     */
    public List<User> getUsers() {
        List<User> list = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement prepStm = conn.prepareStatement("SELECT * FROM users_servlet ORDER BY name")) {
            ResultSet resSet = prepStm.executeQuery();
            while (resSet.next()) {
                int id = resSet.getInt(1);
                String name = resSet.getString(2);
                String login = resSet.getString(3);
                String email = resSet.getString(4);
                LocalDateTime createDate = resSet.getTimestamp(5).toLocalDateTime();
                User user = new User(name, login, email, createDate);
                user.setId(id);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
