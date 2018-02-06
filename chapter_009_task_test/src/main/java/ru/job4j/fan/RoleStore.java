package ru.job4j.fan;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Store for Role entities.
 * @author atrifonov.
 * @version 1.
 * @since 29.01.2018.
 */
public enum RoleStore {
    /**
     * Instance of RoleStore.
     */
    INSTANCE;
    /**
     * Logger.
     */
    private static final Logger ROLE_STORE = LogManager.getLogger(RoleStore.class);
    /**
     * Properties.
     */
    private final Properties prop = new Properties();

    /**
     * Construct RoleStore. Load properties.
     */
    RoleStore() {
        try {
            InputStream is = getClass().getResourceAsStream("config.properties");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            prop.load(br);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try(Connection conn = ConnectionFactory.getDatabaseConnection();
        PreparedStatement createTableRoleFan = conn.prepareStatement(prop.getProperty("createTableRoleFan"))) {

            createTableRoleFan.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds role in database.
     * @param role role
     * @return role with id or null if role have existed.
     */
    public Role create(Role role) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement insertRole = conn.prepareStatement(prop.getProperty("insertRole"))) {

            insertRole.setString(1, role.getName());
            insertRole.setBoolean(2, role.isEditUser());
            insertRole.setBoolean(3, role.isEditRole());
            insertRole.setString(4, role.getName());
            ResultSet setInsertRole = insertRole.executeQuery();
            if(setInsertRole.next()) {
                role.setId(setInsertRole.getInt("id"));
                ROLE_STORE.info(String.format("Create role : id = %d, role_name = %s, edit_user = %b, edit_role = %s",
                        role.getId(), role.getName(), role.isEditUser(), role.isEditRole()));
            } else {
                ROLE_STORE.info(String.format("Try to create role = %s. Role exists.", role.getName()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    /**
     * Update role
     * @param newRole new role.
     */
    public void update(Role newRole) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement updateRole = conn.prepareStatement(prop.getProperty("updateRole"));
            PreparedStatement updateRoleSimp = conn.prepareStatement(prop.getProperty("updateRoleSimp"))) {

            int id = newRole.getId();
            Role oldRole = getRoleById(id);
            int countUpdated = 0;
            if (newRole.getName().equals(oldRole.getName())) {
                updateRoleSimp.setBoolean(1, newRole.isEditUser());
                updateRoleSimp.setBoolean(2, newRole.isEditRole());
                updateRoleSimp.setInt(3, id);

                countUpdated = updateRoleSimp.executeUpdate();
            } else {
                updateRole.setString(1, newRole.getName());
                updateRole.setBoolean(2, newRole.isEditUser());
                updateRole.setBoolean(3, newRole.isEditRole());
                updateRole.setInt(4, id);
                updateRole.setString(5, newRole.getName());

                countUpdated = updateRole.executeUpdate();
            }

            if (countUpdated > 0) {
                ROLE_STORE.info(String.format("Role with id = %d is updated. Current role : role_name = %s, edit_user = %b, edit_role = %s",
                        newRole.getId(), newRole.getName(), newRole.isEditUser(), newRole.isEditRole()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes role by id.
     * @param id id.
     */
    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement deleteRole = conn.prepareStatement(prop.getProperty("deleteRole"))) {

            deleteRole.setInt(1, id);
            int countDeleted = deleteRole.executeUpdate();
            if (countDeleted > 0) {
                ROLE_STORE.info(String.format("Role with id = %d is deleted", id));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get role by id.
     * @param id id.
     * @return Role or null, if not exists.
     */
    public Role getRoleById(int id) {
        Role role = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getRoleById = conn.prepareStatement(prop.getProperty("getRoleById"))) {

            getRoleById.setInt(1, id);
            ResultSet setRoleWithId = getRoleById.executeQuery();
            if(setRoleWithId.next()) {
                role = new Role(setRoleWithId.getString("role_name"), setRoleWithId.getBoolean("edit_user"), setRoleWithId.getBoolean("edit_role"));
                role.setId(id);
            } else {
                ROLE_STORE.info(String.format("RoleStore don't have role with id = %d.", id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * Get role by name.
     * @param roleName name of role.
     * @return Role or null if not exists.
     */
    public Role getRoleByName(String roleName) {
        Role role = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getRoleByName = conn.prepareStatement(prop.getProperty("getRoleByName"))) {

            getRoleByName.setString(1, roleName);
            ResultSet setRoleWithName = getRoleByName.executeQuery();
            if (setRoleWithName.next()) {
                role = new Role(roleName, setRoleWithName.getBoolean("edit_user"), setRoleWithName.getBoolean("edit_role"));
                role.setId(setRoleWithName.getInt("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    /**
     * Gets all users with role.
     * @param role role.
     * @return List of users.
     */
    public List<User> getAllUsers(Role role) {
        List<User> list = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement getAllUsersByRole = conn.prepareStatement(prop.getProperty("getUserByRole"))) {

            getAllUsersByRole.setInt(1, role.getId());
            ResultSet allUsersByRole = getAllUsersByRole.executeQuery();
            while (allUsersByRole.next()) {
                Address address = AddressStore.INSTANCE.getAddress(allUsersByRole.getInt("id_address"));
                User user = new User(allUsersByRole.getString("fan_name"), allUsersByRole.getString("user_login"), role, address,
                        allUsersByRole.getString("password"));
                user.setId(allUsersByRole.getInt("id"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Gets all roles.
     * @return list of roles.
     */
    public List<Role> getRoles() {
        List<Role> list = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getAllRoles = conn.prepareStatement(prop.getProperty("getAllRoles"))) {

            ResultSet allRoles = getAllRoles.executeQuery();
            while (allRoles.next()) {
                Role role = new Role(allRoles.getString("role_name"), allRoles.getBoolean("edit_user"), allRoles.getBoolean("edit_role"));
                role.setId(allRoles.getInt("id"));
                list.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
