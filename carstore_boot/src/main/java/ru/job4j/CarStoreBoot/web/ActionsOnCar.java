package ru.job4j.CarStoreBoot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.job4j.CarStoreBoot.domain.*;
import ru.job4j.CarStoreBoot.service.*;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


@Controller
public class ActionsOnCar {
    private static final String ROOT_STATIC = "static";
    private static final String SAVE_DIRECTORY = "uploadDir";
    @Autowired
    ApplicationContext ctx;
    @Autowired
    ModelService modelService;
    @Autowired
    MakeCarService makeCarService;
    @Autowired
    BodyService bodyService;
    @Autowired
    DriveService driveService;
    @Autowired
    TransmissionService transmService;
    @Autowired
    EngineService engineService;
    @Autowired
    CarService carService;
    @Autowired
    UserService userService;

    @GetMapping("/addCar")
    public ModelAndView viewAddCar() {
        ModelAndView model = new ModelAndView("addCar");
        return model;
    }

    @PostMapping("/addCar")
    public String addCar(@ModelAttribute Car car, @RequestParam String engineType, @RequestParam String driveType,
                         @RequestParam String transmType, @RequestParam String bodyType, @RequestParam String make,
                         @RequestParam String model, @RequestParam("file") MultipartFile file) {
        String fileName = "";
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                ClassLoader classLoader = ctx.getClassLoader();
                URI uri = classLoader.getResource("ru/job4j/CarStoreBoot/Application.class").toURI();
                Path path = Paths.get(uri);
                Iterator<Path> iterator = path.iterator();
                StringBuilder sb = new StringBuilder(path.getRoot().toString());
                while (iterator.hasNext()) {
                    Path x = iterator.next();
                    sb.append(x.toString()).append("\\");
                    if (x.toString().equals("classes")) {
                        break;
                    }
                }
                sb.append(ROOT_STATIC).append(File.separator).append(SAVE_DIRECTORY);
                Path pathTwo = Paths.get(sb.toString());
                if (!Files.exists(pathTwo)) {
                    Files.createDirectory(pathTwo);
                }
                sb.append(File.separator).append(file.getOriginalFilename());
                pathTwo = Paths.get(sb.toString());
                Files.write(pathTwo, bytes);
                fileName = String.format("%s%s%s", SAVE_DIRECTORY, File.separator, file.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        MakeCar makeCar = makeCarService.findByMake(make);
        AutoModel autoModel = modelService.findByModel(model);
        Drive drive = driveService.findByDriveType(driveType);
        Engine engine = engineService.findByEngineType(engineType);
        Transmission transmission = transmService.findByTransmType(transmType);
        Body body = bodyService.findByBodyType(bodyType);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName()).get();
        car.setAutoModel(autoModel);
        car.setBody(body);
        car.setDrive(drive);
        car.setEngine(engine);
        car.setMakeCar(makeCar);
        car.setTransmission(transmission);
        car.setNameImg(fileName);
        car.setSeller(user);
        carService.save(car);
        return "redirect:/start";
    }

    @GetMapping("/editCar")
    public ModelAndView goEdit(@RequestParam("idCar") int id) {
        ModelAndView model = new ModelAndView("start");
        Optional<Car> optional = carService.findById(id);
        if(optional.isPresent()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Car car = optional.get();
            if(auth.getName().equals(car.getSeller().getLogin())) {
                model = new ModelAndView("editCar");;
                model.addObject("car", car);
                List<String> statusList = new ArrayList<>();
                statusList.add("true");
                statusList.add("false");
                model.addObject("statusList", statusList);
                model.addObject("status", Boolean.toString(car.isStatus()));
            }
        }
        return model;
    }

    @PostMapping("/editCar")
    public String editCar(@RequestParam(name = "idCar") int id, @RequestParam String sold) {
        Optional<Car> optional = carService.findById(id);
        if (optional.isPresent()) {
            Car car = optional.get();
            Boolean status = Boolean.parseBoolean(sold);
            car.setStatus(status);
            carService.save(car);
        }
        return "redirect:/start";
    }


    @GetMapping("/deleteCar")
    public ModelAndView deleteCar(@RequestParam(name = "idCar") int id) {
        ModelAndView model = new ModelAndView("start");
        Optional<Car> optional = carService.findById(id);
        if (optional.isPresent()) {
            Car car = optional.get();
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth.getName().equals(car.getSeller().getLogin())) {
                carService.deleteCar(car);
            }
        }
        return model;
    }
}
