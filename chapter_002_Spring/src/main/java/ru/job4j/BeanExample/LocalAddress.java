package ru.job4j.BeanExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Alexandr on 14.03.2018.
 */
@Component
public class LocalAddress {
    private District district;

    @Autowired
    public LocalAddress(District district) {
        this.district = district;
    }

    public District getDistrict() {
        return district;
    }
}
