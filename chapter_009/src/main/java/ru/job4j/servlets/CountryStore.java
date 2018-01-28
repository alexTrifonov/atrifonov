package ru.job4j.servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for store country in database.
 * @author atrifonov.
 * @since 22.01.2018.
 * @version 1.
 */
public enum CountryStore {
    /**
     * Instance of CountryStore.
     */
    INSTANCE;

    /**
     * Construct object of CountryStore.
     */
    CountryStore() {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement createTable = conn.prepareStatement("CREATE TABLE if NOT EXISTS country_store (id SERIAL PRIMARY KEY,"
                    + "country_name VARCHAR (100))");
            PreparedStatement selectRussia = conn.prepareStatement("SELECT * FROM country_store WHERE country_name = 'Russia'")) {

            createTable.executeUpdate();
            ResultSet setRussia = selectRussia.executeQuery();
            if (!setRussia.next()) {
                Country country = new Country("Russia");
                add(country);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add country.
     * @param country country.
     * @return country.
     */
    public Country add(Country country) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement insertCountry = conn.prepareStatement("INSERT INTO country_store (country_name)"
                    + " SELECT ? WHERE NOT EXISTS (SELECT country_name FROM country_store WHERE country_name = ?) RETURNING id;")) {

            insertCountry.setString(1, country.getCountryName());
            insertCountry.setString(2, country.getCountryName());
            ResultSet setInsert = insertCountry.executeQuery();
            if (setInsert.next()) {
                country.setId(setInsert.getInt(1));
            } else {
                country.setId(-1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
    }

    /**
     * Update country.
     * @param newCountry new country.
     */
    public void update(Country newCountry) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement updateCountry = conn.prepareStatement("UPDATE country_store set country_name = ? WHERE id = ?")) {

            updateCountry.setString(1, newCountry.getCountryName());
            updateCountry.setInt(2, newCountry.getId());
            updateCountry.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * Delete country.
     * @param id id of country.
     */
    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement prepStm = conn.prepareStatement("DELETE FROM country_store WHERE id = ?;")) {
            prepStm.setInt(1, id);
            prepStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get country.
     * @param id id of country.
     * @return country.
     */
    public Country getCountry(int id) {
        Country country = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement selectCountry = conn.prepareStatement("SELECT country_name FROM country_store WHERE id = ?")) {
            selectCountry.setInt(1, id);
            ResultSet resultSet = selectCountry.executeQuery();
            if (resultSet.next()) {
                country = new Country(resultSet.getString("country_name"));
                country.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    /**
     * Get list of country.
     * @return list of country.
     */
    public List<Country> getCountries() {
        List<Country> list = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement selectAllCountries = conn.prepareStatement("SELECT * FROM country_store ORDER BY country_name")) {
            ResultSet setCountries = selectAllCountries.executeQuery();
            while (setCountries.next()) {
                int id = setCountries.getInt(1);
                String countryName = setCountries.getString("country_name");
                Country country = new Country(countryName);
                country.setId(id);
                list.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Get country with name.
     * @param nameCountry name of country.
     * @return country.
     */
    public Country getCountry(String nameCountry) {
        Country country = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement selectCountry = conn.prepareStatement("SELECT * FROM country_store WHERE country_name = ?")) {
            selectCountry.setString(1, nameCountry);
            ResultSet resultSet = selectCountry.executeQuery();
            if (resultSet.next()) {
                country = new Country(resultSet.getString("country_name"));
                country.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }
}
