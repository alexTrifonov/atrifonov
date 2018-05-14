package ru.job4j.CarStoreBoot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.CarStoreBoot.domain.*;
import ru.job4j.CarStoreBoot.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test of controller ActionsOnCar
 *
 * @author atrifonov
 * @version 1.
 * @since 11.05.2018
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PartCarController.class)
public class PartCarControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    @MockBean
    private BodyService bodyService;

    @MockBean
    private DriveService driveService;
    @MockBean
    private EngineService engineService;
    @MockBean
    private MakeCarService makeCarService;
    @MockBean
    private ModelService modelService;
    @MockBean
    private RoleService roleService;
    @MockBean
    private TransmissionService transmService;
    @MockBean
    private UserService userService;

    /**
     * Test getEngines.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetEnginesThenReturnJsonEngines() throws Exception {
        Engine engine = new Engine("petrol");
        Engine engineOne = new Engine("diesel");
        given(
                this.engineService.findAll()
        ).willReturn(
                new ArrayList<Engine>(
                        Lists.newArrayList(engine, engineOne)
                )
        );
        this.mvc.perform(
                get("/engine").accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$[0].engineType", is("petrol"))
        ).andExpect(
                jsonPath("$[1].engineType", is("diesel"))
        );
    }

    /**
     * Test getModel.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetModelWithMakeSelectThenReturnJsonEngines() throws Exception {
        MakeCar makeCar = new MakeCar("Honda");
        AutoModel modelCivic = new AutoModel("Civic", makeCar);
        AutoModel modelAccord = new AutoModel("Accord", makeCar);
        given(
                this.makeCarService.findByMake("Honda")
        ).willReturn(makeCar);
        given(
                this.modelService.findByMakeCar(makeCar)
        ).willReturn(
                new ArrayList<AutoModel>(
                        Lists.newArrayList(modelCivic, modelAccord)
                )
        );
        this.mvc.perform(
                get("/model?makeSelect=Honda").accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$[0].model", is("Civic"))
        ).andExpect(
                jsonPath("$[1].model", is("Accord"))
        );
    }

    /**
     * Test getCars.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetCarsWithoutPhotoAnyMakeAnyBodyThenReturnJsonCars() throws Exception {
        User user = new User("user", "password");
        Car car = new Car(1);
        Car carTwo = new Car(2);
        car.setSeller(user);
        car.setStatus(false);
        car.setCost(100000);
        car.setCubicCapacity(2.0);
        car.setNameImg("");
        car.setRunning(200000);
        car.setYear(2001);
        Body body = new Body("sedan");
        Drive drive = new Drive("forward");
        Engine engine = new Engine("petrol");
        MakeCar makeCar = new MakeCar("Honda");
        AutoModel autoModel = new AutoModel("Civic", makeCar);
        Transmission transmission = new Transmission("mechanical");
        car.setBody(body);
        car.setDrive(drive);
        car.setEngine(engine);
        car.setMakeCar(makeCar);
        car.setAutoModel(autoModel);
        car.setTransmission(transmission);

        carTwo.setSeller(user);
        carTwo.setStatus(false);
        carTwo.setCost(100000);
        carTwo.setCubicCapacity(1.6);
        carTwo.setNameImg("");
        carTwo.setRunning(200000);
        carTwo.setYear(2002);
        carTwo.setBody(body);
        carTwo.setDrive(drive);
        carTwo.setEngine(engine);
        carTwo.setMakeCar(makeCar);
        carTwo.setAutoModel(autoModel);
        carTwo.setTransmission(transmission);

        given(
                this.carService.findAll()
        ).willReturn(
                new ArrayList<Car>(
                        Lists.newArrayList(car, carTwo)
                )
        );
        mvc.perform(
                get("/table?makeCar=Make&body=Body&viewPhoto=false").accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$[0].year", is(2001))
        ).andExpect(
                jsonPath("$[1].year", is(2002))
        );

    }

    /**
     * Test getCars.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetCarsWithPhotoAnyMakeAnyBodyThenReturnJsonCars() throws Exception {
        User user = new User("oleg", "password");
        Car car = new Car(1);
        Car carTwo = new Car(2);
        car.setSeller(user);
        car.setStatus(false);
        car.setCost(100000);
        car.setCubicCapacity(2.0);
        car.setNameImg("");
        car.setRunning(200000);
        car.setYear(2001);
        Body body = new Body("sedan");
        Drive drive = new Drive("forward");
        Engine engine = new Engine("petrol");
        MakeCar makeCar = new MakeCar("Honda");
        AutoModel autoModel = new AutoModel("Civic", makeCar);
        Transmission transmission = new Transmission("mechanical");
        car.setBody(body);
        car.setDrive(drive);
        car.setEngine(engine);
        car.setMakeCar(makeCar);
        car.setAutoModel(autoModel);
        car.setTransmission(transmission);
        car.setNameImg("uploadDir\\1.jpg");

        carTwo.setSeller(user);
        carTwo.setStatus(false);
        carTwo.setCost(100000);
        carTwo.setCubicCapacity(1.6);
        carTwo.setNameImg("");
        carTwo.setRunning(200000);
        carTwo.setYear(2002);
        carTwo.setBody(body);
        carTwo.setDrive(drive);
        carTwo.setEngine(engine);
        carTwo.setMakeCar(makeCar);
        carTwo.setAutoModel(autoModel);
        carTwo.setTransmission(transmission);
        carTwo.setNameImg("uploadDir\\2.jpg");

        given(
                this.carService.findByNameImgNot("")
        ).willReturn(
                new ArrayList<Car>(
                        Lists.newArrayList(car, carTwo)
                )
        );
        mvc.perform(
                get("/table?makeCar=Make&body=Body&viewPhoto=true").accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$[0].nameImg", is("uploadDir\\1.jpg"))
        ).andExpect(
                jsonPath("$[1].nameImg", is("uploadDir\\2.jpg"))
        );

    }

    /**
     * Test getCars.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetCarsWithPhotoAndMakeAndBodyThenReturnJsonCars() throws Exception {
        User user = new User("oleg", "password");
        Car car = new Car(1);
        Car carTwo = new Car(2);
        car.setSeller(user);
        car.setStatus(false);
        car.setCost(100000);
        car.setCubicCapacity(2.0);
        car.setNameImg("");
        car.setRunning(200000);
        car.setYear(2001);
        Body body = new Body("sedan");
        Drive drive = new Drive("forward");
        Engine engine = new Engine("petrol");
        MakeCar makeCar = new MakeCar("Honda");
        AutoModel autoModel = new AutoModel("Civic", makeCar);
        Transmission transmission = new Transmission("mechanical");
        car.setBody(body);
        car.setDrive(drive);
        car.setEngine(engine);
        car.setMakeCar(makeCar);
        car.setAutoModel(autoModel);
        car.setTransmission(transmission);
        car.setNameImg("uploadDir\\1.jpg");

        carTwo.setSeller(user);
        carTwo.setStatus(false);
        carTwo.setCost(100000);
        carTwo.setCubicCapacity(1.6);
        carTwo.setNameImg("");
        carTwo.setRunning(200000);
        carTwo.setYear(2002);
        carTwo.setBody(body);
        carTwo.setDrive(drive);
        carTwo.setEngine(engine);
        carTwo.setMakeCar(makeCar);
        carTwo.setAutoModel(autoModel);
        carTwo.setTransmission(transmission);
        carTwo.setNameImg("uploadDir\\2.jpg");

        given(
                this.carService.findByMakeCarAndBodyAndNameImgNot(makeCar, body, "")
        ).willReturn(
                new ArrayList<Car>(
                        Lists.newArrayList(car, carTwo)
                )
        );
        given(
                this.makeCarService.findByMake("Honda")
        ).willReturn(makeCar);
        given(
                this.bodyService.findByBodyType("sedan")
        ).willReturn(body);
        mvc.perform(
                get("/table?makeCar=Honda&body=sedan&viewPhoto=true").accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                jsonPath("$[0].makeCar.make", is("Honda"))
        ).andExpect(
                jsonPath("$[1].body.bodyType", is("sedan"))
        );

    }

    /**
     * Test getCars.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenGetCarsWithPhotoAndMakeAndAnyBodyThenReturnJsonCars() throws Exception {
        User user = new User("oleg", "password");
        Car car = new Car(1);
        Car carTwo = new Car(2);
        car.setSeller(user);
        car.setStatus(false);
        car.setCost(100000);
        car.setCubicCapacity(2.0);
        car.setNameImg("");
        car.setRunning(200000);
        car.setYear(2001);
        Body body = new Body("sedan");
        Drive drive = new Drive("forward");
        Engine engine = new Engine("petrol");
        MakeCar makeCar = new MakeCar("Honda");
        AutoModel autoModel = new AutoModel("Civic", makeCar);
        Transmission transmission = new Transmission("mechanical");
        car.setBody(body);
        car.setDrive(drive);
        car.setEngine(engine);
        car.setMakeCar(makeCar);
        car.setAutoModel(autoModel);
        car.setTransmission(transmission);
        car.setNameImg("uploadDir\\1.jpg");

        carTwo.setSeller(user);
        carTwo.setStatus(false);
        carTwo.setCost(100000);
        carTwo.setCubicCapacity(1.6);
        carTwo.setNameImg("");
        carTwo.setRunning(200000);
        carTwo.setYear(2002);
        Body bodyTwo = new Body("diesel");
        carTwo.setBody(bodyTwo);
        carTwo.setDrive(drive);
        carTwo.setEngine(engine);
        carTwo.setMakeCar(makeCar);
        carTwo.setAutoModel(autoModel);
        carTwo.setTransmission(transmission);
        carTwo.setNameImg("uploadDir\\2.jpg");

        List<Car> list = new ArrayList<Car>(Lists.newArrayList(car, carTwo));
        given(
                this.carService.findByMakeCarAndNameImgNot(makeCar, "")
        ).willReturn(list);
        given(
                this.makeCarService.findByMake("Honda")
        ).willReturn(makeCar);
        ObjectMapper objectMapper = new ObjectMapper();
        mvc.perform(
                get("/table?makeCar=Honda&body=Body&viewPhoto=true").accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isOk()
        ).andExpect(
                content().string(objectMapper.writeValueAsString(list))
        );

    }
}
