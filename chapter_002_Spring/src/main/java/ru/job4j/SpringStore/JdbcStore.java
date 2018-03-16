package ru.job4j.SpringStore;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Store based on database.
 */
public class JdbcStore implements Store {
    private String url;
    private String username;
    private String password;

    public JdbcStore(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

        try (Connection conn = DriverManager.getConnection(url, username, password);
        PreparedStatement createTable = conn.prepareStatement("CREATE TABLE IF NOT EXISTS " +
                "spring_users(id SERIAL PRIMARY KEY, name VARCHAR (100))")) {
            createTable.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(User user) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement add = conn.prepareStatement("INSERT INTO spring_users (name) VALUES (?) RETURNING id")) {
            add.setString(1,user.getName());
            ResultSet resultSet = add.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            } else {
                user.setId(-1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getUsers() {
        List<User> list = new LinkedList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement selectAll = conn.prepareStatement("SELECT * FROM spring_users")) {
            ResultSet resSet = selectAll.executeQuery();
            while (resSet.next()) {
                int id = resSet.getInt(1);
                String name = resSet.getString("name");
                User user = new User(name);
                user.setId(id);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
