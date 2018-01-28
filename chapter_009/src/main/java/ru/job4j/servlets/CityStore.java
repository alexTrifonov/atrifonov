package ru.job4j.servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for store city data in database.
 * @author atrifonov.
 * @version 1.
 * @since 23.01.2018.
 */
public enum CityStore {
    /**
     * Instance of CityStore.
     */
    INSTANCE;

    /**
     * Construct object of CityStore.
     */
    CityStore() {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement createTable = conn.prepareStatement("CREATE TABLE if NOT EXISTS city_store (id SERIAL PRIMARY KEY,"
                    + "city_name VARCHAR (100), id_country INTEGER REFERENCES country_store (id))");
            PreparedStatement selectYekat = conn.prepareStatement("SELECT * FROM city_store WHERE city_name = 'Yekaterinburg' AND id_country = ?");
            PreparedStatement selectRussia = conn.prepareStatement("SELECT * FROM country_store WHERE country_name = 'Russia'")) {

            createTable.executeUpdate();
            ResultSet setRussia = selectRussia.executeQuery();
            if (setRussia.next()) {
                int id_country = setRussia.getInt("id");
                selectYekat.setInt(1, id_country);
                ResultSet setYekat = selectYekat.executeQuery();
                if (!setYekat.next()) {
                    City city = new City("Yekaterinburg", "Russia");
                    add(city);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add city.
     * @param city city.
     * @return city with id.
     */
    public City add(City city) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement insertCity = conn.prepareStatement("INSERT INTO city_store (city_name, id_country)"
                    + " SELECT ?, ? WHERE NOT EXISTS (SELECT 1 FROM city_store WHERE city_name = ? AND id_country = ?) RETURNING id;");
            PreparedStatement selectCountry = conn.prepareStatement("SELECT id FROM country_store WHERE country_name = ?")) {

            selectCountry.setString(1, city.getNameCountry());
            int idCountry = -1;
            ResultSet setCountry = selectCountry.executeQuery();
            if (setCountry.next()) {
                idCountry = setCountry.getInt("id");
            }

            insertCity.setString(1, city.getNameCity());
            insertCity.setInt(2, idCountry);
            insertCity.setString(3, city.getNameCity());
            insertCity.setInt(4, idCountry);


            ResultSet setInsert = insertCity.executeQuery();
            if (setInsert.next()) {
                city.setId(setInsert.getInt(1));
                city.setIdCountry(idCountry);
            } else {
                city.setId(-1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return city;
    }

    /**
     * Update city.
     * @param newCity new city.
     */
    public void update(City newCity) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement updateCity = conn.prepareStatement("UPDATE city_store set city_name = ?, id_country = ?  WHERE id = ?");
            PreparedStatement selectCountry = conn.prepareStatement("SELECT id FROM country_store WHERE contry_name = ?")) {

            selectCountry.setString(1, newCity.getNameCountry());
            int idCountry = -1;
            ResultSet setCountry = selectCountry.executeQuery();
            if (setCountry.next()) {
                idCountry = setCountry.getInt("id");
            }

            updateCity.setString(1, newCity.getNameCity());
            updateCity.setInt(2, idCountry);
            updateCity.setInt(3, newCity.getId());
            updateCity.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    /**
     * Delete city.
     * @param id id of city.
     */
    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement deleteCity = conn.prepareStatement("DELETE FROM city_store WHERE id = ?;")) {
            deleteCity.setInt(1, id);
            deleteCity.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get city.
     * @param id id of city.
     * @return deleted city.
     */
    public City getCity(int id) {
        City city = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement selectCity = conn.prepareStatement("SELECT city_name, id_country FROM city_store WHERE id = ?");
             PreparedStatement selectCountry = conn.prepareStatement("SELECT country_name FROM country_store WHERE id = ?")) {



            selectCity.setInt(1, id);
            ResultSet resultSet = selectCity.executeQuery();
            if (resultSet.next()) {

                selectCountry.setInt(1, resultSet.getInt("id_country"));
                String nameCountry = null;
                ResultSet setCountry = selectCountry.executeQuery();
                if (setCountry.next()) {
                    nameCountry = setCountry.getString("country_name");
                }

                if (nameCountry != null) {
                    city = new City(resultSet.getString("city_name"), nameCountry);
                    city.setId(id);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    /**
     * Get list of all cities in database with same country.
     * @param country country for city.
     * @return list of cities.
     */
    public List<City> getCities(Country country) {
        List<City> list = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement selectAllCities = conn.prepareStatement("SELECT * FROM city_store WHERE id_country = ? ORDER BY city_name");
             PreparedStatement selectIdCountry = conn.prepareStatement("SELECT id FROM country_store WHERE country_name = ?")) {

            int idCountry = -1;
            selectIdCountry.setString(1, country.getCountryName());
            ResultSet setCountry = selectIdCountry.executeQuery();
            if (setCountry.next()) {
                idCountry = setCountry.getInt("id");
            }

            selectAllCities.setInt(1, idCountry);
            ResultSet setCities = selectAllCities.executeQuery();
            while (setCities.next()) {
                int id = setCities.getInt(1);
                String cityName = setCities.getString("city_name");
                City city = new City(cityName, country.getCountryName());
                city.setId(id);
                list.add(city);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
