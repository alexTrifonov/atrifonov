package ru.job4j.CarStoreBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.CarStoreBoot.domain.Drive;
import ru.job4j.CarStoreBoot.repository.DriveRepository;

import java.util.List;

/**
 * Service for drive.
 * @author atrifonov
 * @version 1.
 * @since 26.04.2018.
 */
@Service
public class DriveService {
    @Autowired
    DriveRepository driveRepository;

    public Drive findByDriveType(String driveType) {
        return driveRepository.findByDriveType(driveType);
    }

    public List<Drive> findAll() {
        return (List<Drive>) driveRepository.findAll();
    }
}
