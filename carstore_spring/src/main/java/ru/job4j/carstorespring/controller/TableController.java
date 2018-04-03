package ru.job4j.carstorespring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.carstorespring.models.*;
import ru.job4j.carstorespring.stores.*;

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
    private final String MAKE = "Make";
    private final String BODY = "Body";
    @RequestMapping(value = "/table", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Car> getCars(@RequestParam(name = "makeCar") String makeCar, @RequestParam(name = "body") String body,
                             @RequestParam(name = "viewPhoto") String viewPhoto,  Model model) {

        Boolean withPhoto = Boolean.parseBoolean(viewPhoto);
        String bodyStr = body != null ? body : "";
        String makeCarStr = makeCar != null ? makeCar : "";
        List<Car> carList = new LinkedList<>();

        if (MAKE.equals(makeCarStr) && BODY.equals(bodyStr)) {
            carList = CarStore.INSTANSE.getCars(withPhoto);
        }
        if (!MAKE.equals(makeCarStr) && BODY.equals(bodyStr)) {
            MakeCar make = MakeStore.INSTANCE.getMakeCar(makeCarStr);
            carList = CarStore.INSTANSE.getCars(withPhoto, make);
        }
        if (MAKE.equals(makeCarStr) && !BODY.equals(bodyStr)) {
            Body aBody = BodyStore.INSTANCE.get(bodyStr);
            carList = CarStore.INSTANSE.getCars(withPhoto, aBody);
        }
        if (!MAKE.equals(makeCarStr) && !BODY.equals(bodyStr)) {
            MakeCar make = MakeStore.INSTANCE.getMakeCar(makeCarStr);
            Body aBody = BodyStore.INSTANCE.get(bodyStr);
            carList = CarStore.INSTANSE.getCars(withPhoto, make, aBody);
        }
        return carList;
    }
}
