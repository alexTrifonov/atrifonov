package ru.job4j.carstorespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.carstorespring.crudRepositories.TransmissionRepository;
import ru.job4j.carstorespring.models.Transmission;

import java.util.List;

/**
 * Fill select transmission.
 * @author atrifonov.
 * @version 1.
 * @since 01.04.2018.
 */
@Controller
public class TransmissionController {
    @Autowired
    TransmissionRepository transmissionRepository;

    @RequestMapping(value = "/transmission", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Transmission> getBodies() {
        return (List<Transmission>) transmissionRepository.findAll();
    }
    
}
