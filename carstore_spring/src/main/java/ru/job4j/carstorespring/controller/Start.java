package ru.job4j.carstorespring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * For start page.
 * @author atrifonov.
 * @version 1.
 * @since 01.04.2018.
 */
@Controller
public class Start {
    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String getTable(ModelMap modelMap) {
        return "startPage";
    }
}

