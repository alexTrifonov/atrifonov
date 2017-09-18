package ru.job4j.taskThreads;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ComputeWords implements Runnable {
    private StringBuilder sb;

    public ComputeWords(StringBuilder sb) {
        this.sb = sb;
    }

    @Override
    public void run() {
        compute();
    }

    protected void compute(){
        int countWords= 0;
        Pattern pattern = Pattern.compile("[^\\s]+");
        Matcher matcher = pattern.matcher(sb);
        while (matcher.find()){

            System.out.printf("countWords = %d%n", ++countWords);
        }
    }
}
