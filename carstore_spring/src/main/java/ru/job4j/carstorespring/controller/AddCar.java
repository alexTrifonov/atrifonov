package ru.job4j.carstorespring.controller;

import javax.servlet.ServletContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.carstorespring.models.*;
import ru.job4j.carstorespring.stores.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


/**
 * Controller for adding car in CarStore.
 * @author atrifonov.
 * @version 1.
 * @since 01.04.2018.
 */
@Controller
public class AddCar {
    public static final String SAVE_DIRECTORY = "resources/uploadDir";
    @Autowired
    private ServletContext context;

    @RequestMapping(value = "/addCar", method = RequestMethod.GET)
    public String getPageAddCar(ModelMap modelMap) {
        return "addCar";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCar(@RequestParam String makeCar, @RequestParam(name = "model") String model,
                         @RequestParam(name = "body") String body, @RequestParam(name = "transmission") String transmission,
                         @RequestParam(name = "engine") String engine, @RequestParam(name = "drive") String drive,
                         @RequestParam(name = "capacity") Double capacity, @RequestParam(name = "year") Integer year,
                         @RequestParam(name = "running") Integer running, @RequestParam(name = "cost") Integer cost,
                         @RequestParam(name = "file")MultipartFile file) {

        MakeCar make = MakeStore.INSTANCE.getMakeCar(makeCar);
        AutoModel autoModel = ModelStore.INSTANCE.get(model);
        Body carBody = BodyStore.INSTANCE.get(body);
        Transmission transm = TransmissionStore.INSTANCE.get(transmission);
        Engine carEngine = EngineStore.INSTANCE.get(engine);
        Drive carDrive = DriveStore.INSTANCE.get(drive);
        User user = UserStore.INSTANCE.getUser("igor");

        String fileName = "";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                fileName = file.getOriginalFilename();
                String rootPath = context.getRealPath("");
                File dir = new File(rootPath + File.separator + SAVE_DIRECTORY);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File uploadFile = new File(dir.getAbsoluteFile() + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        session.beginTransaction();
        Car car = new Car();

        car.setMakeCar(make);
        car.setAutoModel(autoModel);
        car.setBody(carBody);
        car.setEngine(carEngine);
        car.setDrive(carDrive);
        car.setTransmission(transm);
        car.setCubicCapacity(capacity);
        car.setYear(year);
        car.setRunning(running);
        car.setStatus(false);
        car.setCost(cost);
        car.setSeller(user);
        car.setSeller(user);
        if (!fileName.isEmpty()) {
            car.setNameImg("resources/uploadDir/" + fileName);
        } else {
            car.setNameImg("");
        }

        CarStore.INSTANSE.add(car);

        return "startPage";
    }
}
