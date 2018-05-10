package ru.job4j.CarStoreBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.CarStoreBoot.domain.MakeCar;
import ru.job4j.CarStoreBoot.repository.MakeRepository;

import java.util.List;

/**
 * Service for make of car.
 * @author atrifonov
 * @version 1.
 * @since 26.04.2018.
 */
@Service
public class MakeCarService {
    @Autowired
    MakeRepository makeRepository;

    public MakeCar findByMake(String make) {
        return makeRepository.findByMake(make);
    }

    public List<MakeCar> findAll() {
        return (List<MakeCar>) makeRepository.findAll();
    }
}
