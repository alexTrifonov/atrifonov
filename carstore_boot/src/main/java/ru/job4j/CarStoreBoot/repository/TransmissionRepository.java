package ru.job4j.CarStoreBoot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.CarStoreBoot.domain.Transmission;

/**
 * Repository for Transmission.
 * @author atrifonov.
 * @version 1.
 * @since 26.04.2018.
 */
public interface TransmissionRepository extends CrudRepository<Transmission, Integer> {
    Transmission findByTransmType(String transmType);
}
