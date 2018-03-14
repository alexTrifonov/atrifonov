package ru.job4j.BeanExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Alexandr on 14.03.2018.
 */
public class Address {
    private City city = new City();
    private Country country;

    public Address(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public Country getCountry() {
        return country;
    }

}
