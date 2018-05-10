package ru.job4j.CarStoreBoot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.CarStoreBoot.domain.Engine;

/**
 * Repository for Engine.
 * @author atrifonov.
 * @version 1.
 * @since 26.04.2018.
 */
public interface EngineRepository extends CrudRepository<Engine, Integer> {
    Engine findByEngineType(String engineType);
}
