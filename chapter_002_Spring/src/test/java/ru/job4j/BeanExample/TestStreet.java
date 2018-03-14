package ru.job4j.BeanExample;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Alexandr on 14.03.2018.
 */
public class TestStreet {
    @Test
    public void whenLoadContextThanStreetIs() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Street street = context.getBean(Street.class);
        Address address = context.getBean(Address.class);
        LocalAddress localAddress = context.getBean(LocalAddress.class);
        assertNotNull(street);
        assertNotNull(address);
        assertNotNull(address.getCountry());
        assertNotNull(address.getCity());
        assertNotNull(localAddress);
        assertNotNull(localAddress.getDistrict());
    }
}
