package ru.job4j.carstorespring.crudRepositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carstorespring.models.Body;

import java.util.List;

/**
 * Repository for Body.
 * @author atrifonov.
 * @version 1.
 * @since 02.04.2018.
 */
public interface BodyRepository extends CrudRepository<Body, Integer> {
    List<Body> findByBodyType(String bodyType);
}
