package ru.job4j.taskSet;

import org.junit.Test;

import java.util.Random;

/**
 * Test
 *
 * @author atrifonov.
 * @since 04.09.2017.
 * @version 1.
 */
public class TestFastSet {
    /**
     * Test add.
     */
    @Test
    public void whenAddItemInFastSetThenTimeLess() {
        int countItem = 100000;

        FastSet<Integer> set = new FastSet<>();
        Random r = new Random();
        long timeStart = System.currentTimeMillis();
        for(int i = 0; i < countItem; i++) {
            set.add(r.nextInt(countItem));
        }
        long timeEnd = System.currentTimeMillis();
        System.out.printf("time adding for FastSet = %d%n", timeEnd - timeStart);

        SimpleSet<Integer> simpleSet = new SimpleSet<>();
        timeStart = System.currentTimeMillis();
        for(int i = 0; i < countItem; i++) {
            simpleSet.add(r.nextInt(countItem));
        }
        timeEnd = System.currentTimeMillis();
        System.out.printf("time adding for SimpleSet = %d%n", timeEnd - timeStart);
    }
}
