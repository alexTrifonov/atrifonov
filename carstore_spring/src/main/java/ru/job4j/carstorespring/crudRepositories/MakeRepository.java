package ru.job4j.carstorespring.crudRepositories;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.carstorespring.models.MakeCar;

import java.util.List;

/**
 * Repository for MakeCar.
 * @author atrifonov.
 * @version 1.
 * @since 02.04.2018.
 */
public interface MakeRepository extends CrudRepository<MakeCar, Integer> {
    List<MakeCar> findByMake(String make);
}
