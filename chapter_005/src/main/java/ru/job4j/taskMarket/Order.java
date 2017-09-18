package ru.job4j.taskMarket;

/**
 * Created by Alexandr on 14.09.2017.
 */
public class Order {
    private String book;
    private String operation;
    private Double price;
    private Integer volume;

    public Order(String book, String operation, Double price, int volume) {
        this.book = book;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
    }

    public String getBook() {
        return book;
    }

    public String getOperation() {
        return operation;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }
}
