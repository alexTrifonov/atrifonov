package ru.job4j.servlets;

/**
 * Class for country entity.
 * @author atrifonov.
 * @since 23.01.2018.
 * @version 1.
 */
public class Country {
    /**
     * name.
     */
    private String countryName;
    /**
     * id.
     */
    private int id;

    /**
     * Construct country.
     * @param countryName name.
     */
    public Country(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Get name of country.
     * @return name.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Get id of country.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set name of country.
     * @param countryName name of country.
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Set if of country.
     * @param id if of country.
     */
    public void setId(int id) {
        this.id = id;
    }
}
