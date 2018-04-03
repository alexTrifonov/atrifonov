package ru.job4j.carstorespring.crudRepositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carstorespring.models.Engine;

/**
 * Repository for Engine.
 * @author atrifonov.
 * @version 1.
 * @since 02.04.2018.
 */
public interface EngineRepository extends CrudRepository<Engine, Integer> {
}
