package ru.job4j.CarStoreBoot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.CarStoreBoot.domain.*;
import ru.job4j.CarStoreBoot.service.*;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Test of controller ActionsOnCar
 *
 * @author atrifonov
 * @version 1.
 * @since 11.05.2018
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ActionsOnCar.class)
public class ActionsOnCarTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    ApplicationContext ctx;

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
     * Test viewAddCar.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "Vasja", roles = {"USER"})
    public void whenAddCarThenPageAddCar() throws Exception {
        this.mvc.perform(
                get("/addCar").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("addCar")
        );
    }

    /**
     * Test addCar.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenAddCarThenSaveCarAndRedirectStart() throws Exception {
        User user = new User("user", "password");
        Optional<User> optional = Optional.of(user);
        Car car = new Car();
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

        MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", new byte[]{});
        given(
                this.bodyService.findByBodyType("sedan")
        ).willReturn(body);
        given(
                this.userService.findByLogin("user")
        ).willReturn(optional);
        given(
                this.driveService.findByDriveType("forward")
        ).willReturn(drive);
        given(
                this.engineService.findByEngineType("petrol")
        ).willReturn(engine);
        given(
                this.makeCarService.findByMake("Honda")
        ).willReturn(makeCar);
        given(
                this.modelService.findByModel("Civic")
        ).willReturn(autoModel);
        given(
                this.transmService.findByTransmType("mechanical")
        ).willReturn(transmission);

        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

        this.mvc.perform(
                multipart("/addCar").file(mockFile).flashAttr("car", car).param("engineType","petrol").param("driveType", "forward").param("bodyType", "sedan")
                .param("make", "Honda").param("model", "Civic").param("transmType", "mechanical")
                .param("_csrf", csrfToken.getToken())
                .sessionAttr("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN", csrfToken)
        ).andExpect(
                status().is3xxRedirection()
        );

        car.setBody(body);
        car.setDrive(drive);
        car.setEngine(engine);
        car.setMakeCar(makeCar);
        car.setAutoModel(autoModel);
        car.setTransmission(transmission);

        verify(this.carService, times(1)).save(car);
        assertThat(car.getNameImg(), is(""));
    }

    /**
     * Test addCar.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenAddCarWithFileThenSaveCarWithFileAndRedirectStart() throws Exception {
        User user = new User("user", "password");
        Optional<User> optional = Optional.of(user);
        Car car = new Car();
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

        MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", new byte[1]);
        given(
                this.bodyService.findByBodyType("sedan")
        ).willReturn(body);
        given(
                this.userService.findByLogin("user")
        ).willReturn(optional);
        given(
                this.driveService.findByDriveType("forward")
        ).willReturn(drive);
        given(
                this.engineService.findByEngineType("petrol")
        ).willReturn(engine);
        given(
                this.makeCarService.findByMake("Honda")
        ).willReturn(makeCar);
        given(
                this.modelService.findByModel("Civic")
        ).willReturn(autoModel);
        given(
                this.transmService.findByTransmType("mechanical")
        ).willReturn(transmission);

        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

        this.mvc.perform(
                multipart("/addCar").file(mockFile).flashAttr("car", car).param("engineType","petrol").param("driveType", "forward").param("bodyType", "sedan")
                        .param("make", "Honda").param("model", "Civic").param("transmType", "mechanical")
                        .param("_csrf", csrfToken.getToken())
                        .sessionAttr("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN", csrfToken)
        ).andExpect(
                status().is3xxRedirection()
        );

        car.setBody(body);
        car.setDrive(drive);
        car.setEngine(engine);
        car.setMakeCar(makeCar);
        car.setAutoModel(autoModel);
        car.setTransmission(transmission);

        verify(this.carService, times(1)).save(car);
        assertThat(car.getNameImg(), is("uploadDir\\test.jpg"));
    }

    /**
     * Test goEdit.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "Vasja", roles = {"USER"})
    public void whenGetEditCarThenPageEditCar() throws Exception {
        Car car = new Car(2);
        User user = new User(2,"Vasja", "password");
        car.setSeller(user);
        car.setStatus(false);
        Optional<Car> optional = Optional.of(car);
        List<String> statusList = new ArrayList<>();
        statusList.add("true");
        statusList.add("false");
        given(
                this.carService.findById(2)
        ).willReturn(optional);

        this.mvc.perform(
                get("/editCar?idCar=2").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("editCar")
        ).andExpect(
                model().attribute("status", Boolean.toString(car.isStatus()))
        ).andExpect(
                model().attribute("statusList", statusList)
        );
    }

    /**
     * Test goEdit.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "Vasja", roles = {"USER"})
    public void whenGetEditNoPresentCarThenPageStart() throws Exception {
        Optional<Car> optional = Optional.empty();
        given(
                this.carService.findById(2)
        ).willReturn(optional);

        this.mvc.perform(
                get("/editCar?idCar=2").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("start")
        );
    }


    /**
     * Test goEdit.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "User", roles = {"USER"})
    public void whenGetEditCarAndUsernameAnotherThenPageStart() throws Exception {
        Car car = new Car(2);
        User user = new User(2,"Vasja", "password");
        car.setSeller(user);
        Optional<Car> optional = Optional.of(car);
        given(
                this.carService.findById(2)
        ).willReturn(optional);

        this.mvc.perform(
                get("/editCar?idCar=2").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("start")
        );
    }

    /**
     * Test editCar.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "User", roles = {"USER"})
    public void whenEditCarThenCarWillHaveNewParamAndRedirectStart() throws Exception {
        Car car = new Car(2);
        car.setStatus(false);
        Optional<Car> optional = Optional.of(car);

        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
        given(
                this.carService.findById(2)
        ).willReturn(optional);

        this.mvc.perform(
                post("/editCar").param("idCar", "2").param("sold", "true").param("_csrf", csrfToken.getToken())
                        .sessionAttr("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN", csrfToken)
        ).andExpect(
                status().is3xxRedirection()
        );
        verify(this.carService, times(1)).save(car);
    }

    /**
     * Test editCar.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "User", roles = {"USER"})
    public void whenEditCarWithInvalidIdThenRedirectStart() throws Exception {
        Car car = new Car(2);
        car.setStatus(false);
        Optional<Car> optional = Optional.empty();

        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
        given(
                this.carService.findById(2)
        ).willReturn(optional);

        this.mvc.perform(
                post("/editCar").param("idCar", "2").param("sold", "true").param("_csrf", csrfToken.getToken())
                        .sessionAttr("org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN", csrfToken)
        ).andExpect(
                status().is3xxRedirection()
        );

        verify(this.carService, times(0)).save(car);
        assertThat(car.isStatus(), is(false));
    }

    /**
     * Test deleteCar.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenDeleteCarAndGetValidIdThenDeleteCarAndPageStart() throws Exception {
        Car car = new Car(1);
        car.setSeller(new User("user", "password"));
        Optional<Car> optional = Optional.of(car);
        given(
                this.carService.findById(1)
        ).willReturn(optional);
        this.mvc.perform(
                get("/deleteCar?idCar=1")
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("start")
        );
        verify(this.carService, times(1)).deleteCar(car);
    }

    /**
     * Test deleteCar.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenDeleteCarAndGetInvalidIdThenPageStart() throws Exception {
        Car car = new Car(1);
        Optional<Car> optional = Optional.empty();
        given(
                this.carService.findById(1)
        ).willReturn(optional);
        this.mvc.perform(
                get("/deleteCar?idCar=1").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("start")
        );
        verify(this.carService, times(0)).deleteCar(car);
    }

    /**
     * Test deleteCar.
     * @throws Exception exception.
     */
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void whenDeleteCarAndGetValidIdAndInvalidSellerThenPageStart() throws Exception {
        Car car = new Car(1);
        car.setSeller(new User("anotherUser", "password"));
        Optional<Car> optional = Optional.of(car);
        given(
                this.carService.findById(1)
        ).willReturn(optional);
        this.mvc.perform(
                get("/deleteCar?idCar=1").accept(MediaType.TEXT_HTML)
        ).andExpect(
                status().isOk()
        ).andExpect(
                view().name("start")
        );
        verify(this.carService, times(0)).deleteCar(car);
    }
}
