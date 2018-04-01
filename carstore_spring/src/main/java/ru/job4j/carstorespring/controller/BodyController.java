package ru.job4j.carstorespring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.carstorespring.models.Body;
import ru.job4j.carstorespring.stores.BodyStore;

/**
 * Fill body.
 * @author atfifonov.
 * @since 01.04.2018.
 */
@Controller
public class BodyController {

    @RequestMapping(value = "/body", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Body> getBodies() {
        return BodyStore.INSTANCE.getBodies();
    }
}
