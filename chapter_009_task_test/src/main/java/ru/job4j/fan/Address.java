package ru.job4j.fan;

/**
 * Class for Address entity.
 */
public class Address {
    /**
     * Id of address in database.
     */
    private int id;
    /**
     * Country of address
     */
    private String Country;
    /**
     * City of address.
     */
    private String City;
    /**
     * Street of address.
     */
    private String Street;
    /**
     * Number of house in address.
     */
    private int houseNumber;
    /**
     * Number of flat in address.
     */
    private int flatNumber;

    /**
     * Construct address with country, city, street, number of house and number of flat.
     * @param country country.
     * @param city city.
     * @param street street.
     * @param houseNumber number of house.
     * @param flatNumber number of flat.
     */
    public Address(String country, String city, String street, int houseNumber, int flatNumber) {
        this.Country = country;
        this.City = city;
        this.Street = street;
        this.houseNumber = houseNumber;
        this.flatNumber = flatNumber;
    }

    /**
     * Getting the country of address.
     * @return country.
     */
    public String getCountry() {
        return Country;
    }

    /**
     * Getting the city of address.
     * @return city.
     */
    public String getCity() {
        return City;
    }

    /**
     * Getting the street of address.
     * @return street.
     */
    public String getStreet() {
        return Street;
    }

    /**
     * Getting the number of house of address.
     * @return number of house.
     */
    public int getHouseNumber() {
        return houseNumber;
    }

    /**
     * Getting the number of flat of address.
     * @return number of flat.
     */
    public int getFlatNumber() {
        return flatNumber;
    }

    /**
     * Getting the id of address.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Setting the country of address.
     * @param country new country.
     */
    public void setCountry(String country) {
        Country = country;
    }

    /**
     * Setting the city of address.
     * @param city new city.
     */
    public void setCity(String city) {
        City = city;
    }

    /**
     * Setting the street of address.
     * @param street new street.
     */
    public void setStreet(String street) {
        Street = street;
    }

    /**
     * Setting the number  of house of address.
     * @param houseNumber new number.
     */
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     * Setting the number of flat of address.
     * @param flatNumber new number.
     */
    public void setFlatNumber(int flatNumber) {
        this.flatNumber = flatNumber;
    }

    /**
     * Setting the id of address.
     * @param id new id.
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %d, %d", Country, City, Street, houseNumber, flatNumber);
    }
}
