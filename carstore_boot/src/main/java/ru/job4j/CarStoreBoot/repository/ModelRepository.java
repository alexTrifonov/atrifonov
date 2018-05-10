package ru.job4j.CarStoreBoot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.CarStoreBoot.domain.AutoModel;
import ru.job4j.CarStoreBoot.domain.MakeCar;

import java.util.List;

/**
 * Repository for AutoModel.
 * @author atrifonov.
 * @version 1.
 * @since 26.04.2018.
 */
public interface ModelRepository extends CrudRepository<AutoModel, Integer> {
    List<AutoModel> findByMakeCar(MakeCar makeCar);
    AutoModel findByModel(String model);
}
