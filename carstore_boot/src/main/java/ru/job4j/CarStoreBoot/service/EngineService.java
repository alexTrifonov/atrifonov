package ru.job4j.CarStoreBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.CarStoreBoot.domain.Engine;
import ru.job4j.CarStoreBoot.repository.EngineRepository;

import java.util.List;

/**
 * Service for engine.
 * @author atrifonov
 * @version 1.
 * @since 26.04.2018.
 */
@Service
public class EngineService {
    @Autowired
    EngineRepository engineRepository;

    public Engine findByEngineType(String engineType) {
        return engineRepository.findByEngineType(engineType);
    }

    public List<Engine> findAll() {
        return (List<Engine>) engineRepository.findAll();
    }
}
