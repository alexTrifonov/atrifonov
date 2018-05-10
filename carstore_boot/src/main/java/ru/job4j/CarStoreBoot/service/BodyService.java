package ru.job4j.CarStoreBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.CarStoreBoot.domain.Body;
import ru.job4j.CarStoreBoot.repository.BodyRepository;

import java.util.List;

/**
 * Service for body.
 * @author atrifonov
 * @version 1.
 * @since 26.04.2018.
 */
@Service
public class BodyService {
    @Autowired
    BodyRepository bodyRepository;

    public Body findByBodyType(String bodyType) {
        return bodyRepository.findByBodyType(bodyType);
    }

    public List<Body> findAll() {
        return (List<Body>) bodyRepository.findAll();
    }
}
