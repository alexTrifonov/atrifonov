package ru.job4j.CarStoreBoot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.CarStoreBoot.domain.Body;

/**
 * Repository for Body.
 * @author atrifonov.
 * @version 1.
 * @since 26.04.2018.
 */
public interface BodyRepository extends CrudRepository<Body, Integer> {
   Body findByBodyType(String bodyType);
}
