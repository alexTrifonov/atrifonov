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
 * Class for store Address entity in database.
 * @author atifonov.
 * @version 1.
 * @since 30.01.2018.
 */
public enum  AddressStore {
    /**
     * Instance of AddressStore.
     */
    INSTANCE;
    /**
     * Logger.
     */
    private static final Logger ADDRESS_STORE = LogManager.getLogger(AddressStore.class);
    /**
     * Properties.
     */
    private final Properties prop = new Properties();

    /**
     * Construct AddressStore. Load properties.
     */
    AddressStore() {
        try {
            InputStream is = getClass().getResourceAsStream("config.properties");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            prop.load(br);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement createTableAddressStore = conn.prepareStatement(prop.getProperty("createTableAddress"))) {

           createTableAddressStore.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds address to database.
     * @param address address.
     * @return address with id or null, if address have existed.
     */
    public Address create(Address address) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
        PreparedStatement insertAddress = conn.prepareStatement(prop.getProperty("insertAddress"))) {

            insertAddress.setString(1, address.getCountry());
            insertAddress.setString(2, address.getCity());
            insertAddress.setString(3, address.getStreet());
            insertAddress.setInt(4, address.getHouseNumber());
            insertAddress.setInt(5, address.getFlatNumber());
            insertAddress.setString(6, address.getCountry());
            insertAddress.setString(7, address.getCity());
            insertAddress.setString(8, address.getStreet());
            insertAddress.setInt(9, address.getHouseNumber());
            insertAddress.setInt(10, address.getFlatNumber());

            ResultSet setInsertAddress = insertAddress.executeQuery();
            if(setInsertAddress.next()) {
                address.setId(setInsertAddress.getInt("id"));
                ADDRESS_STORE.info(String.format("Create address : id = %d, country = %s, city = %s, street = %s, house_number = %d, flat_number = %d",
                        address.getId(), address.getCountry(), address.getCity(), address.getStreet(), address.getHouseNumber(), address.getFlatNumber()));
            } else {
                ADDRESS_STORE.info(String.format("Try to create address : country = %s, city = %s, street = %s, house_number = %d, flat_number = %d. Address exists.",
                        address.getCountry(), address.getCity(), address.getStreet(), address.getHouseNumber(), address.getFlatNumber()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return address;
    }

    /**
     * Updates address.
     * @param address address.
     */
    public void update(Address address) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
        PreparedStatement updateAddress = conn.prepareStatement(prop.getProperty("updateAddress"))) {

            updateAddress.setString(1, address.getCountry());
            updateAddress.setString(2, address.getCity());
            updateAddress.setString(3, address.getStreet());
            updateAddress.setInt(4, address.getHouseNumber());
            updateAddress.setInt(5, address.getFlatNumber());
            updateAddress.setInt(6, address.getId());
            updateAddress.setString(7, address.getCountry());
            updateAddress.setString(8, address.getCity());
            updateAddress.setString(9, address.getStreet());
            updateAddress.setInt(10, address.getHouseNumber());
            updateAddress.setInt(11, address.getFlatNumber());

            int countUpdated = updateAddress.executeUpdate();
            if (countUpdated > 0) {
                ADDRESS_STORE.info(String.format("Address with id = %d updated. Current address : country = %s, city = %s, street = %s, house_number = %d, flat_number = %d",
                        address.getId(), address.getCountry(), address.getCity(), address.getStreet(), address.getHouseNumber(), address.getFlatNumber()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes address by id.
     * @param id id of address.
     */
    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
            PreparedStatement deleteAddress = conn.prepareStatement(prop.getProperty("deleteAddress"))) {

            deleteAddress.setInt(1, id);
            int countDeleted =  deleteAddress.executeUpdate();
            if (countDeleted > 0) {
                ADDRESS_STORE.info(String.format("Address with id = %d deleted.", id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets address by id.
     * @param id id of address.
     * @return address, if address with id have existed.
     */
    public Address getAddress(int id) {
        Address address = null;
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getAddressById = conn.prepareStatement(prop.getProperty("getAddressById"))) {

            getAddressById.setInt(1, id);
            ResultSet setAddressWithId = getAddressById.executeQuery();
            if (setAddressWithId.next()) {
                address = new Address(setAddressWithId.getString("country"), setAddressWithId.getString("city"), setAddressWithId.getString("street"),
                        setAddressWithId.getInt("house_number"), setAddressWithId.getInt("flat_number"));
                address.setId(id);
            } else {
                ADDRESS_STORE.info(String.format("AddressStore don't have address with id = %d.", id));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    /**
     * Gets list of all addresses.
     * @return list of all addresses.
     */
    public List<Address> getAddresses() {
        List<Address> list = new LinkedList<>();
        try (Connection conn = ConnectionFactory.getDatabaseConnection();
             PreparedStatement getAllAddresses = conn.prepareStatement(prop.getProperty("getAllAddresses"))) {

            ResultSet setAllAddresses = getAllAddresses.executeQuery();
            while (setAllAddresses.next()) {
                Address address = new Address(setAllAddresses.getString("country"), setAllAddresses.getString("city"), setAllAddresses.getString("street"),
                        setAllAddresses.getInt("house_number"), setAllAddresses.getInt("flat_number"));
                address.setId(setAllAddresses.getInt("id"));
                list.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
