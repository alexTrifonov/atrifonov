package ru.job4j.carstorespring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.carstorespring.models.Engine;
import ru.job4j.carstorespring.stores.EngineStore;

/**
 * Fill select engine.
 * @author atifonov.
 * @version 1.
 * @since 06.03.2018.
 */
@Controller
public class EngineController {
    @RequestMapping(value = "/engine", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Engine> getEngines() {
        return EngineStore.INSTANCE.getEngines();
    }
}
