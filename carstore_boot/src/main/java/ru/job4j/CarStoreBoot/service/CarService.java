package ru.job4j.CarStoreBoot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.CarStoreBoot.domain.Body;
import ru.job4j.CarStoreBoot.domain.Car;
import ru.job4j.CarStoreBoot.domain.MakeCar;
import ru.job4j.CarStoreBoot.repository.CarRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service for car.
 * @author atrifonov
 * @version 1.
 * @since 26.04.2018.
 */
@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public Car save(Car car) {
        return carRepository.save(car);
    }

    public List<Car> findAll() {
        return (List<Car>) carRepository.findAll();
    }

    public List<Car> findByNameImgNot(String nameImg) {
        return carRepository.findByNameImgNot(nameImg);
    }

    public List<Car> findByMakeCar(MakeCar makeCar) {
        return carRepository.findByMakeCar(makeCar);
    }

    public List<Car> findByBody(Body body) {
        return carRepository.findByBody(body);
    }

    public List<Car> findByMakeCarAndBody(MakeCar makeCar, Body body) {
        return carRepository.findByMakeCarAndBody(makeCar, body);
    }

    public List<Car> findByMakeCarAndNameImgNot(MakeCar makeCar, String nameImg) {
        return carRepository.findByMakeCarAndNameImgNot(makeCar, nameImg);
    }

    public List<Car> findByBodyAndNameImgNot(Body body, String nameImg) {
        return carRepository.findByBodyAndNameImgNot(body, nameImg);
    }
    public List<Car> findByMakeCarAndBodyAndNameImgNot(MakeCar car, Body body, String nameImg) {
        return carRepository.findByMakeCarAndBodyAndNameImgNot(car, body, nameImg);
    }

    public Optional<Car> findById(int id) {
        return carRepository.findById(id);
    }

    public void deleteCar(Car car) {
        carRepository.delete(car);
    }


}
