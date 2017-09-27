package ru.job4j.taskSynchronize;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
/**
 * Test
 */
public class TestUserStorage {
    @Test
    public void whenTransferThenSizesAllAmountIsConstant() {
        UserStorage storage = new UserStorage();
        User userOne = new User(1, 300);
        User userTwo = new User(2, 400);
        storage.add(userOne);
        storage.add(userTwo);

        Runnable a = () -> {
            for(int i = 0; i < 150; i++)
            storage.transfer(1,2, 1);
        };

        Runnable b = () -> {
            for(int i = 0; i < 250; i++)
                storage.transfer(2,1, 1);
        };

        Thread threadA = new Thread(a);
        Thread threadB = new Thread(b);
        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            threadA.interrupt();
            threadB.interrupt();
        }

        int amountOne = userOne.getAmount();
        int amountTwo = userTwo.getAmount();

        assertThat(amountOne == 400 && amountTwo == 300, is(true));

    }
}
