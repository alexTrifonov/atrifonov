package ru.job4j.CarStoreBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.CarStoreBoot.domain.Transmission;
import ru.job4j.CarStoreBoot.repository.TransmissionRepository;

import java.util.List;

/**
 * Service for transmission.
 * @author atrifonov
 * @version 1.
 * @since 26.04.2018.
 */
@Service
public class TransmissionService {
    @Autowired
    TransmissionRepository transmRepository;
    public Transmission findByTransmType(String transmType) {
        return transmRepository.findByTransmType(transmType);
    }

    public List<Transmission> findAll() {
        return (List<Transmission>) transmRepository.findAll();
    }
}
