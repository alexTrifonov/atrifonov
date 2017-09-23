package ru.job4j.taskThreads;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by TrifonovAA on 18.09.2017.
 */
public class ComputeSpaces implements Runnable {
    private StringBuilder sb;

    public ComputeSpaces(StringBuilder sb) {
        this.sb = sb;
    }

    @Override
    public void run() {
        compute();
    }

    protected void compute() {
        int countSpaces = 0;
        char[] chars = this.sb.toString().toCharArray();
        for (char x: chars) {
            if( x == 32) {
                countSpaces++;
            }
            System.out.printf("countSpaces = %d%n", countSpaces);
        }
    }
}
