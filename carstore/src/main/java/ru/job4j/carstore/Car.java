package ru.job4j.carstore;

/**
 * Model of car.
 * @author atrifonov.
 * @since 28.02.2018.
 * @version 1.
 */
public class Car {
    private int id;
    private MakeCar makeCar;
    private AutoModel autoModel;
    private Body body;
    private int year;
    private int running;
    private Transmission transmission;
    private Engine engine;
    private int cubicCapacity;
    private Drive drive;

    public void setId(int id) {
        this.id = id;
    }

    public void setMakeCar(MakeCar makeCar) {
        this.makeCar = makeCar;
    }

    public void setAutoModel(AutoModel autoModel) {
        this.autoModel = autoModel;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRunning(int running) {
        this.running = running;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void setCubicCapacity(int cubicCapacity) {
        this.cubicCapacity = cubicCapacity;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public int getId() {
        return id;
    }

    public MakeCar getMakeCar() {
        return makeCar;
    }

    public AutoModel getAutoModel() {
        return autoModel;
    }

    public Body getBody() {
        return body;
    }

    public int getYear() {
        return year;
    }

    public int getRunning() {
        return running;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public Engine getEngine() {
        return engine;
    }

    public int getCubicCapacity() {
        return cubicCapacity;
    }

    public Drive getDrive() {
        return drive;
    }
}
