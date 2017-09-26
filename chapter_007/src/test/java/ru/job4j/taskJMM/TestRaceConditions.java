package ru.job4j.taskJMM;

/**
 * Created by Alexandr on 26.09.2017.
 */
public class TestRaceConditions {
    private RaceConditions race = new RaceConditions();

    public static void main(String[] args) {
        TestRaceConditions test = new TestRaceConditions();
        test.init();
    }

    private void init() {
        RaceRunnable oneR = new RaceRunnable();
        RaceRunnable twoR = new RaceRunnable();
        Thread trOne = new Thread(oneR);
        Thread trTwo = new Thread(twoR);
        trOne.start();
        trTwo.start();
    }

    public class RaceRunnable implements Runnable {
        @Override
        public void run() {
            boolean finish = false;
            while (!finish) {
                if(race.getNumb() > 0) {
                    race.setNumb(race.getNumb() - 1);
                } else {
                    System.out.printf("race.numb = %d%n", race.getNumb());
                    finish = true;
                }
            }
        }
    }
}
