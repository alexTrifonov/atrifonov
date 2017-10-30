package ru.job4j.optimizationxml;

import java.util.Scanner;

/**
 * Class entry point of application.
 * @author atrifonov.
 * @version 1.
 * @since 24.10.2017.
 */
public class Main {
    /**
     * Entry point.
     * @param args args of cmd
     */
    public static void main(String[] args) {
        String url;
        int count = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to database:");
        url = scanner.nextLine();
        System.out.println("Enter number:");
        boolean enterRight = false;
        while (!enterRight) {
            if (scanner.hasNextInt()) {
                count = scanner.nextInt();
                enterRight = true;
            }
        }
        Performer performer = new Performer();
        performer.setCount(count);
        performer.setUrl(url);
        performer.doWork();
    }
}
