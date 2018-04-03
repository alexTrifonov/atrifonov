package ru.job4j.carstorespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.carstorespring.crudRepositories.MakeRepository;
import ru.job4j.carstorespring.crudRepositories.ModelRepository;
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
    @Autowired
    MakeRepository makeRepository;
    @Autowired
    ModelRepository modelRepository;

    @RequestMapping(value = "/model", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<AutoModel> getBodies(@RequestParam(name = "makeSelect") String makeSelect) {
        String make = makeSelect != null ? makeSelect : "";
        MakeCar makeCar;
        if (make.isEmpty()) {
            //List<MakeCar> list = MakeStore.INSTANCE.getMakes();
            //makeCar = list.iterator().next();
            makeCar = makeRepository.findAll().iterator().next();

        } else {
            //makeCar = MakeStore.INSTANCE.getMakeCar(makeSelect);
            makeCar = makeRepository.findByMake(makeSelect).iterator().next();
        }
        //return ModelStore.INSTANCE.getModels(makeCar);
        System.out.println(String.format("makeCar.getMake() = %s", makeCar.getMake()));
        List<AutoModel> list = modelRepository.findByMakeCar(makeCar);
        System.out.println(String.format("modelRepository.findByMakeCar(makeCar) = %s", list));
        System.out.println("Print list models");
        System.out.println(list.getClass());
        for(AutoModel model : list) {
            System.out.println(String.format("id = %d, model = %s, make = %s", model.getId(), model.getModel(), model.getMakeCar()));
        }
        return modelRepository.findByMakeCar(makeCar);
    }
}
