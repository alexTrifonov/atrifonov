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
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(sb);
        while (matcher.find()){
            System.out.printf("countSpaces = %d%n", ++countSpaces);
        }
    }
}
