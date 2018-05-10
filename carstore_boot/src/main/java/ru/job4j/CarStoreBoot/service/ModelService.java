package ru.job4j.CarStoreBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.CarStoreBoot.domain.AutoModel;
import ru.job4j.CarStoreBoot.domain.MakeCar;
import ru.job4j.CarStoreBoot.repository.ModelRepository;

import java.util.List;

/**
 * Service for model of car.
 * @author atrifonov
 * @version 1.
 * @since 26.04.2018.
 */
@Service
public class ModelService {
    @Autowired
    ModelRepository modelRepository;

    public List<AutoModel> findByMakeCar(MakeCar makeCar) {
        return modelRepository.findByMakeCar(makeCar);
    }

    public AutoModel findByModel(String model) {
        return modelRepository.findByModel(model);
    }
}
