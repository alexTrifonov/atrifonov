package ru.job4j.CarStoreBoot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.CarStoreBoot.domain.MakeCar;

/**
 * Repository for MakeCar.
 * @author atrifonov.
 * @version 1.
 * @since 26.04.2018.
 */
public interface MakeRepository extends CrudRepository<MakeCar, Integer> {
    MakeCar findByMake(String make);
}
