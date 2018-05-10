package ru.job4j.CarStoreBoot.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.CarStoreBoot.domain.Body;
import ru.job4j.CarStoreBoot.domain.Car;
import ru.job4j.CarStoreBoot.domain.MakeCar;

import java.util.List;

/**
 * Repository for Car.
 * @author atrifonov.
 * @version 1.
 * @since 26.04.2018.
 */
public interface CarRepository extends CrudRepository<Car, Integer> {
    List<Car> findByNameImgNot(String nameImg);
    List<Car> findByMakeCar(MakeCar makeCar);
    List<Car> findByBody(Body body);
    List<Car> findByMakeCarAndBody(MakeCar makeCar, Body body);
    List<Car> findByMakeCarAndNameImgNot(MakeCar makeCar, String nameImg);
    List<Car> findByBodyAndNameImgNot(Body body, String nameImg);
    List<Car> findByMakeCarAndBodyAndNameImgNot(MakeCar car, Body body, String nameImg);
}
