package ru.job4j.carstore;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test for CarStore.
 */
public class CarStoreTest {
    private final MakeStore makeStore = MakeStore.INSTANCE;
    private final ModelStore modelStore = ModelStore.INSTANCE;
    private final EngineStore engineStore = EngineStore.INSTANCE;
    private final BodyStore bodyStore = BodyStore.INSTANCE;
    private final DriveStore driveStore = DriveStore.INSTANCE;
    private final TransmissionStore transmissionStore = TransmissionStore.INSTANCE;
    private final CarStore carStore = CarStore.INSTANSE;
    private final UserStore userStore = UserStore.INSTANCE;

    /**
     * Test for getCars with photo, makeCar and Body.
     */
    @Test
    public void getCarsWithPhotoMakeAndBody() {
        MakeCar makeCar = new MakeCar("make");
        makeStore.add(makeCar);
        AutoModel autoModel = new AutoModel("model", makeCar);
        modelStore.add(autoModel);
        Body body = new Body();
        body.setBodyType("body");
        body = bodyStore.add(body);
        Body bodyTwo = new Body();
        bodyTwo.setBodyType("bodyTwo");
        bodyTwo = bodyStore.add(bodyTwo);
        Drive drive = new Drive();
        drive.setDriveType("drive");
        driveStore.add(drive);
        Engine engine = new Engine();
        engine.setEngineType("engine");
        engineStore.add(engine);
        Transmission transmission = new Transmission();
        transmission.setTransmType("transmission");
        transmissionStore.add(transmission);
        User user = new User("vasja", "pass");
        userStore.add(user);
        Car car = new Car();
        car.setCost(2);
        car.setTransmission(transmission);
        car.setDrive(drive);
        car.setEngine(engine);
        car.setMakeCar(makeCar);
        car.setCubicCapacity(1.2);
        car.setBody(body);
        car.setSeller(user);
        car.setStatus(false);
        car.setAutoModel(autoModel);
        car.setNameImg("");
        car.setRunning(1);
        car.setYear(1);
        car = carStore.add(car);

        Car carWithImage = new Car();
        carWithImage.setCost(2);
        carWithImage.setTransmission(transmission);
        carWithImage.setDrive(drive);
        carWithImage.setEngine(engine);
        carWithImage.setMakeCar(makeCar);
        carWithImage.setCubicCapacity(1.2);
        carWithImage.setBody(bodyTwo);
        carWithImage.setSeller(user);
        carWithImage.setStatus(false);
        carWithImage.setAutoModel(autoModel);
        carWithImage.setNameImg("./133.jpg");
        carWithImage.setRunning(1);
        carWithImage.setYear(1);
        carWithImage = carStore.add(carWithImage);


        List<Car> carList = new LinkedList<>();
        carList.add(car);
        List<Car> carListOnlyImage = new LinkedList<>();
        carListOnlyImage.add(carWithImage);

        List<Car> cars = carStore.getCars(false, makeCar, body);
        List<Car> carsWithImage = carStore.getCars(true, makeCar, bodyTwo);
        boolean result = carList.equals(cars) && carListOnlyImage.equals(carsWithImage);

        assertThat(result, is(true));
    }
}
