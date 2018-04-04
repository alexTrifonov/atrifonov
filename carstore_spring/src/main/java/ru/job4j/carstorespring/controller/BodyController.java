package ru.job4j.carstorespring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.carstorespring.crudRepositories.BodyRepository;
import ru.job4j.carstorespring.models.Body;

/**
 * Fill body.
 * @author atfifonov.
 * @since 01.04.2018.
 */
@Controller
public class BodyController {

    @Autowired
    private BodyRepository bodyRepository;

    @RequestMapping(value = "/body", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Body> getBodies() {
        return (List<Body>) bodyRepository.findAll();
    }
}
