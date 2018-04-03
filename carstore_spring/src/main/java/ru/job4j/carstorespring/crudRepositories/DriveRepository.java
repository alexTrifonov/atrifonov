package ru.job4j.carstorespring.crudRepositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carstorespring.models.Drive;

/**
 * Repository for Drive.
 * @author atrifonov.
 * @version 1.
 * @since 02.04.2018.
 */
public interface DriveRepository extends CrudRepository<Drive, Integer> {
}
