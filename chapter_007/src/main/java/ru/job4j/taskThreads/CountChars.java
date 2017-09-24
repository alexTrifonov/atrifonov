package ru.job4j.taskThreads;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class CountChars implements Runnable {

    @Override
    public void run() {
        int countChar = 0;
        try {
            File file = new File(System.getProperty("user.dir") + "\\chapter_007\\crsto10.txt");
            Scanner scanner = new Scanner(file, "UTF-8");
            String line;
            boolean exit = false;
            while (scanner.hasNext() && !exit) {
                line = scanner.next();
                countChar += line.length();
                if(Thread.currentThread().isInterrupted()){
                    exit = true;
                    System.out.println();
                    System.out.printf("%s is interrupted %n", Thread.currentThread().getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("count of chars = %d%n", countChar);
    }
}
