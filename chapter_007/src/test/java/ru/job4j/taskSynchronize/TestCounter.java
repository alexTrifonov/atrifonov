package ru.job4j.taskSynchronize;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
/**
 * Created by Alexandr on 27.09.2017.
 */
public class TestCounter {
    @Test
    public void ThenTwoThreadIncrementCountThenTheyAreDoingThisThreadSafety() {
        Counter counter = new Counter();
        Runnable r = () ->{
            for(int i = 0; i < 50000; i++) {
                counter.increment();
            }
        };

        Runnable rn = () ->{
            for(int i = 0; i < 100000; i++) {
                counter.increment();
            }
        };

        Thread one = new Thread(r);
        Thread two = new Thread(rn);
        one.start();
        two.start();
        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            one.interrupt();
            two.interrupt();
        }
        assertThat(counter.getCount(), is(150000));

    }
}
