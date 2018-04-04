package ru.job4j.carstorespring.crudRepositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carstorespring.models.Drive;

import java.util.List;

/**
 * Repository for Drive.
 * @author atrifonov.
 * @version 1.
 * @since 02.04.2018.
 */
public interface DriveRepository extends CrudRepository<Drive, Integer> {
    List<Drive> findByDriveType(String driveType);
}
