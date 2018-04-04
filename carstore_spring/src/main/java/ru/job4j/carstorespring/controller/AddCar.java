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
import ru.job4j.carstorespring.crudRepositories.*;
import ru.job4j.carstorespring.models.*;

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
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private BodyRepository bodyRepository;
    @Autowired
    private DriveRepository driveRepository;
    @Autowired
    private EngineRepository engineRepository;
    @Autowired
    private MakeRepository makeRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private TransmissionRepository transmissionRepository;
    @Autowired
    private UserRepository userRepository;

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

        MakeCar make = makeRepository.findByMake(makeCar).iterator().next();
        AutoModel autoModel = modelRepository.findByModel(model).iterator().next();
        Body carBody = bodyRepository.findByBodyType(body).iterator().next();
        Transmission transm = transmissionRepository.findByTransmType(transmission).iterator().next();
        Engine carEngine = engineRepository.findByEngineType(engine).iterator().next();
        Drive carDrive = driveRepository.findByDriveType(drive).iterator().next();
        User user = userRepository.findByLogin("igor").iterator().next();

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

        carRepository.save(car);

        return "startPage";
    }
}
