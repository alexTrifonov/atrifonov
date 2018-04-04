package ru.job4j.carstorespring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.carstorespring.crudRepositories.MakeRepository;
import ru.job4j.carstorespring.models.MakeCar;

/**
 * Fill select make.
 * @author atrifonov.
 * @version 1.
 * @since 01.04.2018.
 */
@Controller
public class MakeCarController {
    @Autowired
    MakeRepository makeRepository;

    @RequestMapping(value = "/make", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<MakeCar> getBodies() {
        return (List<MakeCar>) makeRepository.findAll();
    }
}
