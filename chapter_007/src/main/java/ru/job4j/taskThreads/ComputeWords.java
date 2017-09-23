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
        char[] chars = this.sb.toString().toCharArray();
        boolean prevWrd = true;
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == 32) {
                countWords = (prevWrd) ? ++countWords : countWords;
                prevWrd = false;
            } else {
                prevWrd = true;
            }

            if(i == chars.length - 1 && prevWrd) {
                countWords++;
            }
            System.out.printf("countWords = %d%n", countWords);
        }
    }
}
