package ru.job4j.carstorespring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.carstorespring.stores.*;
import ru.job4j.carstorespring.models.*;

import java.util.List;

/**
 * Fill select model.
 * @author atrifonov.
 * @version 1.
 * @since 06.03.2018.
 */
@Controller
public class ModelController {
    @RequestMapping(value = "/model", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<AutoModel> getBodies(@RequestParam(name = "makeSelect") String makeSelect) {
        String make = makeSelect != null ? makeSelect : "";
        MakeCar makeCar;
        if (make.isEmpty()) {
            List<MakeCar> list = MakeStore.INSTANCE.getMakes();
            makeCar = list.iterator().next();
        } else {
            makeCar = MakeStore.INSTANCE.getMakeCar(makeSelect);
        }
        return ModelStore.INSTANCE.getModels(makeCar);
    }
}
