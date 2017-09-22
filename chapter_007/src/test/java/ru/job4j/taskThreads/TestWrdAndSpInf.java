package ru.job4j.taskThreads;

import org.junit.Test;


public class TestWrdAndSpInf {
    @Test
    public void ifToInterruptThenCountSpacesAndWordsWillBeIncorrect() {
        System.out.println("START");
        WrdAndSpInf wrdAndSpInf = new WrdAndSpInf(10000000);
        Thread trSp = new Thread(wrdAndSpInf.getrCompSp());
        Thread trWrd = new Thread(wrdAndSpInf.getrCompWrd());
        trSp.start();
        trWrd.start();

        try {
            Thread.sleep(1000);
            trSp.interrupt();
            trWrd.interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("FINISH");
    }
}
