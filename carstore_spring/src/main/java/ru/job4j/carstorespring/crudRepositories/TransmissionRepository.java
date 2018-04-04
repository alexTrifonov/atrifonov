package ru.job4j.carstorespring.crudRepositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carstorespring.models.Transmission;

import java.util.List;

/**
 * Repository for Transmission.
 * @author atrifonov.
 * @version 1.
 * @since 02.04.2018.
 */
public interface TransmissionRepository extends CrudRepository<Transmission, Integer> {
    List<Transmission> findByTransmType(String transmType);
}
