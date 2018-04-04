package ru.job4j.carstorespring.crudRepositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carstorespring.models.AutoModel;
import ru.job4j.carstorespring.models.MakeCar;

import java.util.List;

/**
 * Repository for AutoModel.
 * @author atrifonov.
 * @version 1.
 * @since 02.04.2018.
 */
public interface ModelRepository extends CrudRepository<AutoModel, Integer> {
    List<AutoModel> findByMakeCar(MakeCar makeCar);
    List<AutoModel> findByModel(String model);
}
