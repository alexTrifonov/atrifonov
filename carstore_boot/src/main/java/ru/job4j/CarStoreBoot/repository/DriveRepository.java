package ru.job4j.CarStoreBoot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.CarStoreBoot.domain.Drive;

/**
 * Repository for Drive.
 * @author atrifonov.
 * @version 1.
 * @since 26.04.2018.
 */
public interface DriveRepository extends CrudRepository<Drive, Integer> {
    Drive findByDriveType(String driveType);
}
