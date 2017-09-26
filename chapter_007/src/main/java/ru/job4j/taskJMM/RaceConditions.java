package ru.job4j.taskJMM;


public class RaceConditions {
    private int numb = 50000;

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }
}
