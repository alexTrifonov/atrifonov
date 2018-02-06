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
 * Class for store relation Users and MusicTypes.
 * @author atrifonov.
 * @version 1.
 * @since 31.01.2018.
 */
public enum UserMusicStore {
    /**
     * Instance of UserMusicStore.
     */
    INSTANCE;
    /**
     * Logger.
     */
    private static final Logger USER_MUSIC_STORE = LogManager.getLogger(UserMusicStore.class);
    /**
     * Properties.
     */
    private final Properties prop = new Properties();

    /**
     * Construct UserMusicStore. Load properties.
     */
    UserMusicStore() {
        try {
            InputStream is = getClass().getResourceAsStream("config.properties");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            prop.load(br);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement createTableUserMusics = conn.prepareStatement(prop.getProperty("createTableUserMusics"))) {

            createTableUserMusics.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add relation user and musicType.
     * @param user user.
     * @param musicType musicType.
     */
    public void createUserMusic(User user, MusicType musicType) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement createUserMusic = conn.prepareStatement(prop.getProperty("createUserMusic"))) {

            createUserMusic.setInt(1, user.getId());
            createUserMusic.setInt(2, musicType.getId());
            createUserMusic.setInt(3, user.getId());
            createUserMusic.setInt(4, musicType.getId());

            int countCreated = createUserMusic.executeUpdate();
            if (countCreated > 0) {
                USER_MUSIC_STORE.info(String.format("login = %s added in musicList MusicType = %s", user.getLogin(), musicType.getType()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Delete relation user and musicType.
     * @param user user.
     * @param musicType musicType.
     */
    public void deleteUserMusic(User user, MusicType musicType) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement delUserMusic = conn.prepareStatement(prop.getProperty("deleteUserMusic"))) {
            delUserMusic.setInt(1, user.getId());
            delUserMusic.setInt(2, musicType.getId());
            int countDeleted = delUserMusic.executeUpdate();
            if (countDeleted > 0) {
                USER_MUSIC_STORE.info(String.format("login %s deleted musicType = %s in musicList", user.getLogin(), musicType.getType()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete all relations this user with musicTypes.
     * @param user user.
     */
    public void deleteAllUserMusic(User user) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
        PreparedStatement delAllUserMusic = conn.prepareStatement(prop.getProperty("deleteAllUserMusic"))) {

            delAllUserMusic.setInt(1, user.getId());
            int countDeleted = delAllUserMusic.executeUpdate();
            if (countDeleted > 0) {
                USER_MUSIC_STORE.info(String.format("login %s deleted all musicTypes in musicList", user.getLogin()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get user by musicType.
     * @param musicType musicType.
     * @return user.
     */
    public User getUserByMusicType(MusicType musicType) {
        User user = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getUserByMusicType = conn.prepareStatement(prop.getProperty("getUserIdByMusic"))) {

            getUserByMusicType.setInt(1, musicType.getId());
            ResultSet setUserWithMusic = getUserByMusicType.executeQuery();
            if(setUserWithMusic.next()) {
                int id = setUserWithMusic.getInt("id_user");
                user = UserStore.INSTANCE.getUserById(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Get all user musicTypes.
     * @param user user.
     * @return list of musicTypes.
     */
    public List<MusicType> getAllUserMusic(User user) {
        List<MusicType> list = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getAllIdMusicUser = conn.prepareStatement(prop.getProperty("getAllIdMusicUser"))) {

            getAllIdMusicUser.setInt(1, user.getId());
            ResultSet setIdMusic = getAllIdMusicUser.executeQuery();
            while (setIdMusic.next()) {
                int id_music = setIdMusic.getInt("id_music_type");
                MusicType musicType = MusicStore.INSTANCE.getMusicType(id_music);
                if (musicType != null) {
                    list.add(musicType);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
