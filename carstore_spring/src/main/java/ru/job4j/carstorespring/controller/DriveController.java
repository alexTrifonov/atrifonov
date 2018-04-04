package ru.job4j.carstorespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.carstorespring.crudRepositories.DriveRepository;
import ru.job4j.carstorespring.models.Drive;

import java.util.List;

/**
 * Fill select drive.
 * @author atrifonov.
 * @since 06.03.2018.
 */
@Controller
public class DriveController {
    @Autowired
    DriveRepository driveRepository;

    @RequestMapping(value = "/drive", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Drive> getBodies() {
        return (List<Drive>) driveRepository.findAll();
    }
}
