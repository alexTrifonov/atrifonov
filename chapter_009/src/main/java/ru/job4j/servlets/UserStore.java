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
public enum UserStore {
    /**
     * Instance of UserStore.
     */
    INSTANCE;
    /**
     * Logger.
     */
    private static final Logger USER_STORE = LogManager.getLogger(UserStore.class);
    /**
     * Construct UserStore.
     */
    UserStore() {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("CREATE TABLE if NOT EXISTS users_servlet(id SERIAL PRIMARY KEY, "
                     + "name VARCHAR(100), login VARCHAR(100), email VARCHAR (100), id_role INTEGER REFERENCES role_store (id), create_date TIMESTAMP, password VARCHAR(100));");
             PreparedStatement selectAll = conn.prepareStatement("SELECT * FROM users_servlet;");
             PreparedStatement selectAdminRole = conn.prepareStatement("SELECT * FROM role_store WHERE role_name = 'admin'");
             PreparedStatement insertAdmin = conn.prepareStatement("INSERT INTO users_servlet (name, login, email, id_role, create_date, password) VALUES"
                     + " ('admin', 'admin', 'mail', ?, '12 01 2018, 21:38:30', 'password')")
        ) {
            preparedStatement.executeUpdate();
            ResultSet setAll = selectAll.executeQuery();
            if (!setAll.next()) {
                ResultSet setRole = selectAdminRole.executeQuery();
                setRole.next();
                int idRole = setRole.getInt("id");
                insertAdmin.setInt(1, idRole);
                insertAdmin.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add user to database.
     * @param user user for adding.
     * @return user with id.
     */
    public User add(User user) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement prepStm = conn.prepareStatement("INSERT INTO users_servlet (name, login, email, id_role, create_date, password) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;");
             PreparedStatement prepStmSelect = conn.prepareStatement("SELECT * FROM users_servlet WHERE users_servlet.name = ? AND users_servlet.login = ? "
                     + "AND users_servlet.email = ? AND users_servlet.id_role = ? AND users_servlet.create_date = ? AND users_servlet.password = ?;");
             PreparedStatement prepStmRole = conn.prepareStatement("SELECT * FROM role_store WHERE role_store.role_name = ?")) {
            prepStmRole.setString(1, user.getRoleName());
            ResultSet setRole = prepStmRole.executeQuery();
            setRole.next();
            int idRole = setRole.getInt("id");

            prepStmSelect.setString(1, user.getName());
            prepStmSelect.setString(2, user.getLogin());
            prepStmSelect.setString(3, user.getEmail());
            prepStmSelect.setInt(4, idRole);
            prepStmSelect.setTimestamp(5, Timestamp.valueOf(user.getCreateDate()));
            prepStmSelect.setString(6, user.getPassword());
            ResultSet resSet = prepStmSelect.executeQuery();

            if (!resSet.next()) {
                prepStm.setString(1, user.getName());
                prepStm.setString(2, user.getLogin());
                prepStm.setString(3, user.getEmail());
                prepStm.setInt(4, idRole);
                prepStm.setTimestamp(5, Timestamp.valueOf(user.getCreateDate()));
                prepStm.setString(6, user.getPassword());
                ResultSet resultSetInsert = prepStm.executeQuery();
                if (resultSetInsert.next()) {
                    int id = resultSetInsert.getInt(1);
                    user.setId(id);
                    USER_STORE.info(String.format("Insert user: id = %d, name = %s, login = %s, email = %s, roleName = %d, create_date = %s%n",
                            id, user.getName(), user.getLogin(), user.getEmail(), idRole,
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
             PreparedStatement prepStm = conn.prepareStatement("UPDATE users_servlet set name = ?, login = ?, email = ?, id_role = ?, create_date = ?, password = ? WHERE id = ? ;");
             PreparedStatement prepStmRole = conn.prepareStatement("SELECT * FROM role_store WHERE role_store.role_name = ?")) {
            prepStmRole.setString(1, newUser.getRoleName());
            ResultSet setRole = prepStmRole.executeQuery();
            setRole.next();
            int idRole = setRole.getInt("id");

            prepStm.setString(1, newUser.getName());
            prepStm.setString(2, newUser.getLogin());
            prepStm.setString(3, newUser.getEmail());
            prepStm.setInt(4, idRole);
            prepStm.setTimestamp(5, Timestamp.valueOf(newUser.getCreateDate()));
            prepStm.setString(6, newUser.getPassword());
            prepStm.setInt(7, newUser.getId());
            prepStm.executeUpdate();
            USER_STORE.info(String.format("Update user with id = %d: name = %s, login = %s, email = %s, id_role = %d, create_date = %s%n", newUser.getId(),
                    newUser.getName(), newUser.getLogin(), newUser.getEmail(), idRole,
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
                USER_STORE.info(String.format("Delete user with id = %d: name = %s, login = %s, email = %s, id_role = %d, create_date = %s%n",
                        resultSet.getInt(1), resultSet.getString("name"), resultSet.getString("login"), resultSet.getString("email"), resultSet.getInt("id_role"),
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
             PreparedStatement getUserFromDB = conn.prepareStatement("SELECT name, login, email, id_role, create_date, password FROM users_servlet WHERE id = ?");
             PreparedStatement prepStRoleName = conn.prepareStatement("SELECT role_name FROM role_store WHERE id = ?")) {
            getUserFromDB.setInt(1, id);
            ResultSet resultSet = getUserFromDB.executeQuery();
            if (resultSet.next()) {
                prepStRoleName.setInt(1, resultSet.getInt("id_role"));
                ResultSet setRole = prepStRoleName.executeQuery();
                LocalDateTime dateTime = resultSet.getTimestamp("create_date").toLocalDateTime();
                setRole.next();
                user = new User(resultSet.getString("name"), resultSet.getString("login"), resultSet.getString("email"), setRole.getString("role_name"), dateTime,
                        resultSet.getString("password"));
                user.setId(id);
            }
        } catch (SQLException e) {
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
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getUserFromDB = conn.prepareStatement("SELECT id, name, login, email, id_role, create_date, password FROM users_servlet WHERE login = ?");
             PreparedStatement prepStRoleName = conn.prepareStatement("SELECT role_name FROM role_store WHERE id = ?")) {
            getUserFromDB.setString(1, login);
            ResultSet resultSet = getUserFromDB.executeQuery();
            if (resultSet.next()) {
                prepStRoleName.setInt(1, resultSet.getInt("id_role"));
                ResultSet setRole = prepStRoleName.executeQuery();
                LocalDateTime dateTime = resultSet.getTimestamp("create_date").toLocalDateTime();
                setRole.next();
                user = new User(resultSet.getString("name"), resultSet.getString("login"), resultSet.getString("email"), setRole.getString("role_name"), dateTime,
                        resultSet.getString("password"));
                user.setId(resultSet.getInt("id"));
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
             PreparedStatement prepStm = conn.prepareStatement("SELECT * FROM users_servlet ORDER BY name");
             PreparedStatement prepStRoleName = conn.prepareStatement("SELECT role_name FROM role_store WHERE id = ?")) {
            ResultSet resSet = prepStm.executeQuery();
            ResultSet setRole = null;
            while (resSet.next()) {
                int id = resSet.getInt(1);
                String name = resSet.getString(2);
                String login = resSet.getString(3);
                String email = resSet.getString(4);
                int roleId = resSet.getInt(5);
                prepStRoleName.setInt(1, roleId);
                setRole = prepStRoleName.executeQuery();
                setRole.next();
                LocalDateTime createDate = resSet.getTimestamp(6).toLocalDateTime();
                String password = resSet.getString(7);
                User user = new User(name, login, email, setRole.getString("role_name"), createDate, password);
                user.setId(id);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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
             PreparedStatement prepStm = conn.prepareStatement("SELECT * FROM users_servlet WHERE login = ? AND password = ?;")) {
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
