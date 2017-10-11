package ru.job4j.nonBlocking;

/**
 * Class for model.
 * @author atrifonov.
 * @version 1.
 * @since 11.10.2017.
 */
public class Model {
    /**
     * The name of model.
     */
    private String name;
    /**
     * The id of model.
     */
    private Integer id;
    /**
     * The version of model. Variable for correct update.
     */
    private int version;

    /**
     * Construct model with all fields.
     * @param name the name of model.
     * @param id the id of model.
     * @param version the version of model.
     */
    public Model(String name, Integer id, int version) {
        this.name = name;
        this.id = id;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
