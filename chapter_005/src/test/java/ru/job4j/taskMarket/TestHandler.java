package ru.job4j.taskMarket;

import org.junit.Test;

public class TestHandler {
    @Test
    public void whenGetFileOrdersThenPrintBookOrders() {
        Handler handler = new Handler();
        long timeStart = System.currentTimeMillis();
        handler.createAndPrintBook();
        long timeFinish = System.currentTimeMillis();
        System.out.println();
        System.out.println();
        System.out.printf("time = %d, ms",timeFinish - timeStart);
    }
}
