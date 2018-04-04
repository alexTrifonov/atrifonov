package ru.job4j.carstorespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.carstorespring.crudRepositories.BodyRepository;
import ru.job4j.carstorespring.crudRepositories.CarRepository;
import ru.job4j.carstorespring.crudRepositories.MakeRepository;
import ru.job4j.carstorespring.models.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Fill table on start page.
 * @author atifonov.
 * @version 1.
 * @since 01.04.2018.
 */
@Controller
public class TableController{
    private static final String MAKE = "Make";
    private static final String BODY = "Body";

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private MakeRepository makeRepository;
    @Autowired
    private BodyRepository bodyRepository;

    @RequestMapping(value = "/table", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Car> getCars(@RequestParam(name = "makeCar") String makeCar, @RequestParam(name = "body") String body,
                             @RequestParam(name = "viewPhoto") String viewPhoto,  Model model) {

        Boolean withPhoto = Boolean.parseBoolean(viewPhoto);
        String bodyStr = body != null ? body : "";
        String makeCarStr = makeCar != null ? makeCar : "";
        List<Car> carList = new LinkedList<>();
        MakeCar make;
        Body aBody;
        if (MAKE.equals(makeCarStr) && BODY.equals(bodyStr)) {
            if (!withPhoto) {
                carList = (List<Car>) carRepository.findAll();
            } else {
                carList = carRepository.findByNameImgNot("");
            }
        }
        if (!MAKE.equals(makeCarStr) && BODY.equals(bodyStr)) {
            make = makeRepository.findByMake(makeCarStr).iterator().next();
            if(!withPhoto) {
                carList = carRepository.findByMakeCar(make);
            } else {
                carList = carRepository.findByMakeCarAndNameImgNot(make, "");
            }
        }
        if (MAKE.equals(makeCarStr) && !BODY.equals(bodyStr)) {
            aBody = bodyRepository.findByBodyType(bodyStr).iterator().next();
            if (!withPhoto) {
                carList = carRepository.findByBody(aBody);
            } else {
                carList = carRepository.findByBodyAndNameImgNot(aBody, "");
            }
        }
        if (!MAKE.equals(makeCarStr) && !BODY.equals(bodyStr)) {
            aBody = bodyRepository.findByBodyType(bodyStr).iterator().next();
            make = makeRepository.findByMake(makeCarStr).iterator().next();
            if(!withPhoto) {
                carList = carRepository.findByMakeCarAndBody(make, aBody);
            } else {
                carList = carRepository.findByMakeCarAndBodyAndNameImgNot(make, aBody, "");
            }
        }
        return carList;
    }
}
