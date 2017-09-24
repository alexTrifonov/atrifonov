package ru.job4j.taskThreads;

/**
 * Class for check execution time.
 */
public class Time implements Runnable {
    /**
     * Execution time.
     */
    private int timeWork;
    private long timeStart;

    /**
     * Construct Time with execution time and time start.
     * @param timeWork the time for work.
     * @param timeStart the start time of application.
     */
    public Time(int timeWork, long timeStart) {
        this.timeWork = timeWork;
        this.timeStart = timeStart;
    }

    @Override
    public void run() {
        System.out.printf("timeStart = %d%n", this.timeStart);
        while (!Thread.currentThread().isInterrupted()) {
            long timeFinish = System.currentTimeMillis();
            if(timeFinish - timeStart > timeWork) {
                System.out.printf("timeFinish = %d%n", timeFinish);
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " is interrupted");
            }
        }
    }


}
