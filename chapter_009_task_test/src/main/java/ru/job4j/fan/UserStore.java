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
import java.util.LinkedList;
import java.util.List;
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
     * Logger.
     */
    private static final Logger USER_STORE = LogManager.getLogger(UserStore.class);
    /**
     * Properties.
     */
    private Properties prop = new Properties();

    /**
     * Construct UserStore. Load properties.
     */
    UserStore() {
        try {
            InputStream is = getClass().getResourceAsStream("config.properties");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            prop.load(br);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement createTableFan = conn.prepareStatement(prop.getProperty("createTableFan"))) {

            createTableFan.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add user in database.
     * @param user user.
     * @return User or null if user have existed.
     */
    public User create(User user) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement insertUser = conn.prepareStatement(prop.getProperty("insertUser"))) {


            if(user.getAddress().getId() == 0) {
                Address address = AddressStore.INSTANCE.create(user.getAddress());
                user.getAddress().setId(address.getId());
            }

            if (user.getAddress().getId() != 0) {
                insertUser.setString(1, user.getName());
                insertUser.setString(2, user.getLogin());
                insertUser.setInt(3, user.getRole().getId());
                insertUser.setInt(4, user.getAddress().getId());
                insertUser.setString(5, user.getPassword());
                insertUser.setString(6, user.getLogin());
                insertUser.setInt(7, user.getAddress().getId());

                ResultSet setInsertUser = insertUser.executeQuery();
                if(setInsertUser.next()) {
                    user.setId(setInsertUser.getInt("id"));
                    USER_STORE.info(String.format("Create user : id = %d, fan_name = %s, user_login = %s, id_role = %d, id_address = %d, password = %s",
                            user.getId(), user.getName(), user.getLogin(), user.getRole().getId(), user.getAddress().getId(), user.getPassword()));
                } else {
                    USER_STORE.info(String.format("Try to create user with login = %s or address = %s. User exists.",
                            user.getLogin(), user.getAddress()));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Update user.
     * @param newUser new user.
     */
    public void update(User newUser) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement updateUser = conn.prepareStatement(prop.getProperty("updateUser"));
            PreparedStatement updateUserSimp = conn.prepareStatement(prop.getProperty("updateUserSimp"));
            PreparedStatement updateUserByLog = conn.prepareStatement(prop.getProperty("updateUserByLog"));
            PreparedStatement updateUserByAddr = conn.prepareStatement(prop.getProperty("updateUserByAddr"))) {

            int id = newUser.getId();
            User oldUser = getUserById(id);
            int countUpdated = 0;
            if (oldUser.getLogin().equals(newUser.getLogin()) && oldUser.getAddress().toString().equals(newUser.getAddress().toString()))  {
                updateUserSimp.setString(1, newUser.getName());
                updateUserSimp.setInt(2, newUser.getRole().getId());
                updateUserSimp.setString(3, newUser.getPassword());
                updateUserSimp.setInt(4, newUser.getId());

                countUpdated = updateUserSimp.executeUpdate();
            } else if (oldUser.getLogin().equals(newUser.getLogin()) && !oldUser.getAddress().toString().equals(newUser.getAddress().toString())) {
                updateUserByLog.setString(1, newUser.getName());
                updateUserByLog.setInt(2, newUser.getRole().getId());
                Address address = AddressStore.INSTANCE.create(newUser.getAddress());
                updateUserByLog.setInt(3, address.getId());
                updateUserByLog.setString(4, newUser.getPassword());
                updateUserByLog.setInt(5, newUser.getId());
                updateUserByLog.setInt(6, address.getId());

                countUpdated = updateUserByLog.executeUpdate();
            } else if (oldUser.getAddress().toString().equals(newUser.getAddress().toString()) && !oldUser.getLogin().equals(newUser.getLogin())) {
                updateUser.setString(1, newUser.getName());
                updateUser.setString(2, newUser.getLogin());
                updateUser.setInt(3, newUser.getRole().getId());
                updateUser.setString(5, newUser.getPassword());
                updateUser.setInt(6, newUser.getId());
                updateUser.setString(7, newUser.getLogin());

                countUpdated = updateUserByAddr.executeUpdate();
            } else if (!oldUser.getLogin().equals(newUser.getLogin()) && !oldUser.getAddress().toString().equals(newUser.getAddress().toString())) {
                updateUser.setString(1, newUser.getName());
                updateUser.setString(2, newUser.getLogin());
                updateUser.setInt(3, newUser.getRole().getId());
                Address address = AddressStore.INSTANCE.create(newUser.getAddress());
                updateUser.setInt(4, address.getId());
                updateUser.setString(5, newUser.getPassword());
                updateUser.setInt(6, newUser.getId());
                updateUser.setString(7, newUser.getLogin());
                updateUser.setInt(8, address.getId());

                countUpdated = updateUser.executeUpdate();
            }

            if(countUpdated > 0 ) {
                USER_STORE.info(String.format("User with id = %d is updated. Current user :" +
                                " fan_name = %s, user_login = %s, id_role = %d, id_address = %d, password = %s",
                        newUser.getId(), newUser.getName(), newUser.getLogin(), newUser.getRole().getId(),
                        newUser.getAddress().getId(), newUser.getPassword()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete user by id.
     * @param id id.
     */
    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement deleteUser = conn.prepareStatement(prop.getProperty("deleteUser"))) {

            deleteUser.setInt(1, id);
            int countDeleted = deleteUser.executeUpdate();
            if(countDeleted > 0) {
                USER_STORE.info(String.format("User with id = %d is deleted.", id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get user by id.
     * @param id id.
     * @return User or null if not exists.
     */
    public User getUserById(int id) {
        User user = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getUserById = conn.prepareStatement(prop.getProperty("getUserById"))) {

            getUserById.setInt(1, id);
            ResultSet setUserWithId = getUserById.executeQuery();
            if(setUserWithId.next()) {
                Role role = RoleStore.INSTANCE.getRoleById(setUserWithId.getInt("id_role"));
                Address address = AddressStore.INSTANCE.getAddress(setUserWithId.getInt("id_address"));
                user = new User(setUserWithId.getString("fan_name"), setUserWithId.getString("user_login"), role, address,
                        setUserWithId.getString("password"));
                user.setId(id);
            } else {
                USER_STORE.info("UserStore don't have user with id = %d", id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Get user by address.
     * @param address address.
     * @return User or null if not exists.
     */
    public User getUserByAddress(Address address) {
        User user = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
        PreparedStatement getUserByAddress = conn.prepareStatement(prop.getProperty("getUserByAddress"))) {
            getUserByAddress.setInt(1, address.getId());
            ResultSet setUserWithAddr = getUserByAddress.executeQuery();
            if(setUserWithAddr.next()) {
                Role role = RoleStore.INSTANCE.getRoleById(setUserWithAddr.getInt("id_role"));
                user = new User(setUserWithAddr.getString("fan_name"), setUserWithAddr.getString("user_login"), role, address,
                        setUserWithAddr.getString("password"));
                user.setId(setUserWithAddr.getInt("id"));
            } else {
                USER_STORE.info("UserStore don't have user with id_address = %d", address.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Get user by role.
     * @param role role.
     * @return User or null if not exists.
     */
    public User getUserByRole(Role role) {
        User user = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getUserByRole = conn.prepareStatement(prop.getProperty("getUserByRole"))) {
            getUserByRole.setInt(1, role.getId());
            ResultSet setUserWithRole = getUserByRole.executeQuery();
            if(setUserWithRole.next()) {
                Address address = AddressStore.INSTANCE.getAddress(setUserWithRole.getInt("id_address"));
                user = new User(setUserWithRole.getString("fan_name"), setUserWithRole.getString("user_login"), role, address,
                        setUserWithRole.getString("password"));
                user.setId(setUserWithRole.getInt("id"));
            } else {
                USER_STORE.info("UserStore don't have user with role_name = %s", role.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Get user by musicType.
     * @param musicType musicType.
     * @return User.
     */
    public User getUserByMusicType(MusicType musicType) {
        return UserMusicStore.INSTANCE.getUserByMusicType(musicType);
    }

    /**
     * Insert User and his role, address in database.
     * @param name name of user.
     * @param login login of user.
     * @param role role of user.
     * @param address address of user.
     * @param password password of user.
     * @return User or null if user already exists in database.
     */
    public User insertUserAndEntity(String name, String login, Role role, Address address, String password) {
        User user = null;
        if (role.getId() == 0) {
            role = RoleStore.INSTANCE.create(role);
        }
        if (address.getId() == 0) {
            address = AddressStore.INSTANCE.create(address);
        }
        user = new User(name, login, role, address, password);
        user = create(user);
        return user;
    }

    /**
     * Get users.
     * @param withAdmin If with admin = true list all users, else list all users with role "user".
     * @return list users.
     */
    public List<User> getUsers(boolean withAdmin) {
        List<User> list = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getAllUsers = conn.prepareStatement(prop.getProperty("getAllUsers"));
             PreparedStatement getAllNoAdmin = conn.prepareStatement(prop.getProperty("getAllNoAdminNoModerator"))) {

            ResultSet allUsers;
            if (withAdmin) {
                allUsers = getAllUsers.executeQuery();
            } else {
                Role admin = RoleStore.INSTANCE.getRoleByName("admin");
                Role moderator = RoleStore.INSTANCE.getRoleByName("moderator");
                int idAdmin = 0;
                int idModerator = 0;
                if (admin != null) {
                    idAdmin = admin.getId();
                }
                if (moderator != null) {
                    idModerator = moderator.getId();
                }

                getAllNoAdmin.setInt(1, idAdmin);
                getAllNoAdmin.setInt(2, idModerator);
                allUsers = getAllNoAdmin.executeQuery();
            }

            while (allUsers.next()) {
                Role role = RoleStore.INSTANCE.getRoleById(allUsers.getInt("id_role"));
                Address address = AddressStore.INSTANCE.getAddress(allUsers.getInt("id_address"));
                User user = new User(allUsers.getString("fan_name"), allUsers.getString("user_login"), role, address,
                        allUsers.getString("password"));
                user.setId(allUsers.getInt("id"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * Check user with login and password in database is available.
     * @param login login of user.
     * @param password password of user.
     * @return true if user is available.
     */
    public int availableId(String login, String password) {
        int id = 0;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement prepStm = conn.prepareStatement(prop.getProperty("checkLoginPassword"))) {
            prepStm.setString(1, login);
            prepStm.setString(2, password);
            ResultSet resultSet = prepStm.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
                USER_STORE.info(String.format("Get id = %d", id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
