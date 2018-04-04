package ru.job4j.carstorespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.carstorespring.crudRepositories.MakeRepository;
import ru.job4j.carstorespring.crudRepositories.ModelRepository;
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
    private MakeRepository makeRepository;
    @Autowired
    private ModelRepository modelRepository;

    @RequestMapping(value = "/model", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<AutoModel> getModels(@RequestParam(name = "makeSelect") String makeSelect) {
        String make = makeSelect != null ? makeSelect : "";
        MakeCar makeCar;
        if (make.isEmpty()) {
            makeCar = makeRepository.findAll().iterator().next();
        } else {
            makeCar = makeRepository.findByMake(makeSelect).iterator().next();
        }
        return modelRepository.findByMakeCar(makeCar);
    }
}
