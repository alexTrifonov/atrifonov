package ru.job4j.servlets;

/**
 * Class for city.
 * @author atrifonov.
 * @version 1.
 * @since 22.04.2018.
 */
public class City {
    /**
     * city.
     */
    private String nameCity;
    /**
     * country.
     */
    private String nameCountry;
    /**
     * id.
     */
    private int id;
    /**
     * idCountry.
     */
    private int idCountry;

    /**
     * Construct city.
     * @param nameCity name of city.
     * @param nameCountry name of country.
     */
    public City(String nameCity, String nameCountry) {
        this.nameCity = nameCity;
        this.nameCountry = nameCountry;
    }

    /**
     * get name of city.
     * @return name of city.
     */
    public String getNameCity() {
        return nameCity;
    }

    /**
     * Get name of country.
     * @return name of country.
     */
    public String getNameCountry() {
        return nameCountry;
    }

    /**
     * Get id of city.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set name of city.
     * @param nameCity name of city.
     */
    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    /**
     * Set name of country.
     * @param nameCountry name of country.
     */
    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    /**
     * Set id of city.
     * @param id id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get id of country.
     * @return id of country.
     */
    public int getIdCountry() {
        return idCountry;
    }

    /**
     * Set id of country.
     * @param idCountry id of country.
     */
    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }
}
