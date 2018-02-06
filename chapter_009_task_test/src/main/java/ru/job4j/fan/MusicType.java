package ru.job4j.fan;

/**
 * Class for entity MusicType.
 * @author atrifonov.
 * @version 1.
 * @since 28.01.2018.
 */
public class MusicType {
    /**
     * Id of musicType in database.
     */
    private int id;
    /**
     * Type of music.
     */
    private String type;

    /**
     * Construct MusicType.
     * @param type type of music.
     */
    public MusicType(String type) {
        this.type = type;
    }

    /**
     * Getting type.
     * @return type.
     */
    public String getType() {
        return type;
    }

    /**
     * Getting id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setting id.
     * @param id id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setting type.
     * @param type type.
     */
    public void setType(String type) {
        this.type = type;
    }
}
