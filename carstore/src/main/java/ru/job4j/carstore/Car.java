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
    private double cubicCapacity;
    private Drive drive;
    private User seller;
    private Integer cost;
    private boolean status;
    private String nameImg;

    public Car() {

    }

    public Car(int id) {
        this.id = id;
    }

    public String getNameImg() {
        return nameImg;
    }

    public void setNameImg(String nameImg) {
        this.nameImg = nameImg;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public User getSeller() {

        return seller;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

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

    public void setCubicCapacity(double cubicCapacity) {
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

    public double getCubicCapacity() {
        return cubicCapacity;
    }

    public Drive getDrive() {
        return drive;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %.1f, %s, %s, %d, %d, %d", makeCar, autoModel, body, engine, cubicCapacity,
                drive, transmission, year, running, cost);
    }
}
