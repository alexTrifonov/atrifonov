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
 * Class for store MusicType entity in database.
 * @author atrifonov.
 * @version 1.
 * @since 30.01.2018.
 */
public enum MusicStore {
    /**
     * Instance of MusicStore.
     */
    INSTANCE;
    /**
     * Logger.
     */
    private static final Logger MUSIC_STORE = LogManager.getLogger(MusicStore.class);
    /**
     * Properties.
     */
    private final Properties prop = new Properties();

    /**
     * Construct MusicStore. Load properties.
     */
    MusicStore() {
        try {
            InputStream is = getClass().getResourceAsStream("config.properties");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            prop.load(br);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement createTableMusicType = conn.prepareStatement(prop.getProperty("createTableMusicType"))) {

            createTableMusicType.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds musicType in database.
     * @param musicType musicType
     * @return musicType with id or null if musicType have existed.
     */
    public MusicType create(MusicType musicType) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement insertMusicType = conn.prepareStatement(prop.getProperty("insertMusicType"))) {

            insertMusicType.setString(1, musicType.getType());
            insertMusicType.setString(2, musicType.getType());
            ResultSet setInsertMusic = insertMusicType.executeQuery();
            if(setInsertMusic.next()) {
                musicType.setId(setInsertMusic.getInt("id"));
                MUSIC_STORE.info(String.format("Create MusicType : id = %d, type = %s", musicType.getId(), musicType.getType()));
            } else {
                MUSIC_STORE.info(String.format("Try to create MusicType with type = %s. MusicType exists. ", musicType.getType()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicType;
    }

    /**
     * Update musicType
     * @param musicType musicType.
     */
    public void update(MusicType musicType) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement updateMusicType = conn.prepareStatement(prop.getProperty("updateMusicType"))) {

            int id = musicType.getId();
            MusicType oldMusicType = getMusicType(id);
            if (!oldMusicType.getType().equals(musicType.getType())) {
                updateMusicType.setString(1, musicType.getType());
                updateMusicType.setInt(2, musicType.getId());
                updateMusicType.setString(3, musicType.getType());
                int countUpdated = updateMusicType.executeUpdate();
                if (countUpdated > 0) {
                    MUSIC_STORE.info(String.format("MusicType with id = %d is updated. Current MusicType : type = %s", musicType.getId(),  musicType.getType()));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete musicType by id.
     * @param id id of musicType.
     */
    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement deleteMusicType = conn.prepareStatement(prop.getProperty("deleteMusicType"))) {

            deleteMusicType.setInt(1, id);
            int countDeleted = deleteMusicType.executeUpdate();
            if (countDeleted > 0) {
                MUSIC_STORE.info(String.format("MusicType with id = %d is deleted.", id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets musicType by id.
     * @param id id.
     * @return musicType.
     */
    public MusicType getMusicType(int id) {
        MusicType musicType = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement getMusicTypeById = conn.prepareStatement(prop.getProperty("getMusicTypeById"))) {

            getMusicTypeById.setInt(1, id);
            ResultSet setMusicWithId = getMusicTypeById.executeQuery();
            if (setMusicWithId.next()) {
                musicType = new MusicType(setMusicWithId.getString("type"));
                musicType.setId(id);
            } else {
                MUSIC_STORE.info(String.format("MusicStore don't have MusicType with id = %d.", id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicType;
    }

    /**
     * Gets musicType by type.
     * @param type type.
     * @return musicType.
     */
    public MusicType getMusicTypeByType(String type) {
        MusicType musicType = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getMusicTypeByType = conn.prepareStatement(prop.getProperty("getMusicTypeByType"))) {

            getMusicTypeByType.setString(1, type);
            ResultSet setMusicWithType = getMusicTypeByType.executeQuery();
            if (setMusicWithType.next()) {
                musicType = new MusicType(setMusicWithType.getString("type"));
                musicType.setId(setMusicWithType.getInt("id"));
            } else {
                MUSIC_STORE.info(String.format("MusicStore don't have MusicType with type = %s.", type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicType;
    }

    /**
     * Gets list of all musicTypes.
     * @return list.
     */
    public List<MusicType> getMusicTypes() {
        List<MusicType> list = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getAllMusicType = conn.prepareStatement(prop.getProperty("getAllMusicType"))) {

            ResultSet setAllMusic = getAllMusicType.executeQuery();
            while (setAllMusic.next()) {
                MusicType musicType = new MusicType(setAllMusic.getString("type"));
                musicType.setId(setAllMusic.getInt("id"));
                list.add(musicType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
