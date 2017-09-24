package ru.job4j.taskThreads;


public class TestTimeAndCountChars {
    public static void main(String[] args) {
        long timeStart = System.currentTimeMillis();
        Time time = new Time(100, timeStart);
        CountChars countChars = new CountChars();
        Thread threadTime = new Thread(time);
        Thread charThread = new Thread(countChars);
        threadTime.start();
        charThread.start();
        boolean finish = false;
        while (!finish) {
            if(!threadTime.isAlive()) {
                charThread.interrupt();
                finish = true;
            }
        }
    }
}
