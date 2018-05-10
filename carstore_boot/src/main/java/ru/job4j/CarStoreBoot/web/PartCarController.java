package ru.job4j.CarStoreBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.job4j.CarStoreBoot.domain.*;
import ru.job4j.CarStoreBoot.service.*;

import java.util.LinkedList;
import java.util.List;

@Controller
public class PartCarController {
    private static final String MAKE = "Make";
    private static final String BODY = "Body";
    @Autowired
    EngineService engineService;

    @Autowired
    DriveService driveService;

    @Autowired
    BodyService bodyService;

    @Autowired
    TransmissionService transmService;

    @Autowired
    MakeCarService makeService;

    @Autowired
    ModelService modelService;

    @Autowired
    CarService carService;
    @Autowired
    UserService userService;
    @RequestMapping(value = "/engine", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Engine> getEngines() {
        return engineService.findAll();
    }

    @RequestMapping(value = "/drive", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Drive> getDrives() {
        return driveService.findAll();
    }

    @RequestMapping(value = "/transmission", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Transmission> getTransmission() {
        return transmService.findAll();
    }

    @RequestMapping(value = "/body", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Body> getBody() {
        return bodyService.findAll();
    }

    @RequestMapping(value = "/make", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<MakeCar> getMake() {
        return makeService.findAll();
    }

    @RequestMapping(value = "/model", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<AutoModel> getModel(@RequestParam String makeSelect) {
        MakeCar makeCar = makeService.findByMake(makeSelect);
        return modelService.findByMakeCar(makeCar);
    }

    @RequestMapping(value = "/table", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public List<Car> getCars(@RequestParam String makeCar, @RequestParam String body, @RequestParam String viewPhoto) {
        Boolean withPhoto = Boolean.parseBoolean(viewPhoto);
        String bodyStr = body != null ? body : "";
        String makeCarStr = makeCar != null ? makeCar : "";
        List<Car> carList = new LinkedList<>();
        MakeCar make;
        Body aBody;
        if (MAKE.equals(makeCarStr) && BODY.equals(bodyStr)) {
            if (!withPhoto) {
                carList = carService.findAll();
            } else {
                carList = carService.findByNameImgNot("");
            }
        }
        if (!MAKE.equals(makeCarStr) && BODY.equals(bodyStr)) {
            make = makeService.findByMake(makeCarStr);
            if(!withPhoto) {
                carList = carService.findByMakeCar(make);
            } else {
                carList = carService.findByMakeCarAndNameImgNot(make, "");
            }
        }
        if (MAKE.equals(makeCarStr) && !BODY.equals(bodyStr)) {
            aBody = bodyService.findByBodyType(bodyStr);
            if (!withPhoto) {
                carList = carService.findByBody(aBody);
            } else {
                carList = carService.findByBodyAndNameImgNot(aBody, "");
            }
        }
        if (!MAKE.equals(makeCarStr) && !BODY.equals(bodyStr)) {
            aBody = bodyService.findByBodyType(bodyStr);
            make = makeService.findByMake(makeCarStr);
            if(!withPhoto) {
                carList = carService.findByMakeCarAndBody(make, aBody);
            } else {
                carList = carService.findByMakeCarAndBodyAndNameImgNot(make, aBody, "");
            }
        }
        return carList;
    }
}
