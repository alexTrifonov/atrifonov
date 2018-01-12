package ru.job4j.servlets;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Store for roles.
 * @author atrifonov.
 * @version 1.
 * @since 09.01.2018.
 */
public enum  RoleStore {
    /**
     * Instance of RoleStore.
     */
    INSTANCE;

    /**
     * Construct RoleStore with create database.
     */
    RoleStore() {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement prepStm = conn.prepareStatement("CREATE TABLE if NOT EXISTS role_store (id SERIAL PRIMARY KEY ,"
                    + "role_name VARCHAR(100), can_update_user boolean, can_add_user boolean, can_delete_user boolean, can_change_role boolean, can_add_role boolean);");
            PreparedStatement selectAll = conn.prepareStatement("SELECT * FROM role_store;");) {
            prepStm.executeUpdate();
            ResultSet setAll = selectAll.executeQuery();
            if (!setAll.next()) {
                add(new Role("admin", true, true, true, true, true));
                add(new Role("user", true, false, false, false, false));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add new role to database.
     * @param role role for adding.
     * @return role.
     */
    public Role add(Role role) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement prepStm = conn.prepareStatement("INSERT INTO role_store (role_name, can_update_user, can_add_user, can_delete_user, can_change_role, can_add_role) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;");
             PreparedStatement prepStmSelect = conn.prepareStatement("SELECT * FROM role_store WHERE role_store.role_name = ? AND role_store.can_update_user = ? "
                     + "AND role_store.can_add_user = ? AND role_store.can_delete_user = ? AND role_store.can_change_role = ? AND role_store.can_add_role = ?;")) {
            prepStmSelect.setString(1, role.getRoleName());
            prepStmSelect.setBoolean(2, role.isCanUpdateUser());
            prepStmSelect.setBoolean(3, role.isCanAddUser());
            prepStmSelect.setBoolean(4, role.isCanDeleteUser());
            prepStmSelect.setBoolean(5, role.isCanChangeRole());
            prepStmSelect.setBoolean(6, role.isCanAddRole());
            ResultSet resSet = prepStmSelect.executeQuery();

            if (!resSet.next()) {
                prepStm.setString(1, role.getRoleName());
                prepStm.setBoolean(2, role.isCanUpdateUser());
                prepStm.setBoolean(3, role.isCanAddUser());
                prepStm.setBoolean(4, role.isCanDeleteUser());
                prepStm.setBoolean(5, role.isCanChangeRole());
                prepStm.setBoolean(6, role.isCanAddRole());
                ResultSet resultSetInsert = prepStm.executeQuery();
                if (resultSetInsert.next()) {
                    int id = resultSetInsert.getInt(1);
                    role.setId(id);
                }
            } else {
                role.setId(-1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * Update role.
     * @param newRole updated role.
     */
    public void update(Role newRole) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement prepStm = conn.prepareStatement("UPDATE role_store set role_store.role_name = ? AND role_store.can_update_user = ? "
                     + "AND role_store.can_add_user = ? AND role_store.can_delete_user = ? AND role_store.can_change_role = ? AND role_store.can_add_role = ? WHERE id = ? ;")) {
            prepStm.setString(1, newRole.getRoleName());
            prepStm.setBoolean(2, newRole.isCanUpdateUser());
            prepStm.setBoolean(3, newRole.isCanAddUser());
            prepStm.setBoolean(4, newRole.isCanDeleteUser());
            prepStm.setBoolean(5, newRole.isCanChangeRole());
            prepStm.setBoolean(6, newRole.isCanAddRole());
            prepStm.setInt(7, newRole.getId());
            prepStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete role from database.
     * @param id id of role.
     */
    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement prepStm = conn.prepareStatement("DELETE FROM role_store WHERE id = ?;")) {
            prepStm.setInt(1, id);
            prepStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get role with assigned id.
     * @param id id of role.
     * @return role.
     */
    public Role getRole(int id) {
        Role role = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getRoleFromDB = conn.prepareStatement("SELECT role_name, can_update_user, can_add_user, can_delete_user, can_change_role, can_add_role"
                     + " FROM role_store WHERE id = ?")) {
            getRoleFromDB.setInt(1, id);
            ResultSet resultSet = getRoleFromDB.executeQuery();
            if (resultSet.next()) {
                role = new Role(resultSet.getString("roleName"), resultSet.getBoolean("canUpdateUser"), resultSet.getBoolean("canAddUser"),
                        resultSet.getBoolean("canDeleteUser"), resultSet.getBoolean("canChangeRole"), resultSet.getBoolean("canAddRole"));
                role.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * Get list of all roles.
     * @return list of roles.
     */
    public List<Role> getRoles() {
        List<Role> list = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement prepStm = conn.prepareStatement("SELECT * FROM role_store ORDER BY role_name")) {
            ResultSet resSet = prepStm.executeQuery();
            while (resSet.next()) {
                int id = resSet.getInt(1);
                String roleName = resSet.getString("role_name");
                boolean canUpdateUser = resSet.getBoolean("can_update_user");
                boolean canAddUser = resSet.getBoolean("can_add_user");
                boolean canDeleteUser = resSet.getBoolean("can_delete_user");
                boolean canChangeRole = resSet.getBoolean("can_change_role");
                boolean canAddRole = resSet.getBoolean("can_add_role");
                Role role = new Role(roleName, canUpdateUser, canAddUser, canDeleteUser, canChangeRole, canAddRole);
                role.setId(id);
                list.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
