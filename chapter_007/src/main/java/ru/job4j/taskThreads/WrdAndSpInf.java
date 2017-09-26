package ru.job4j.taskThreads;

import java.util.Random;


public class WrdAndSpInf {
    private String str;
    private int sizeStr;

    public WrdAndSpInf(int sizeStr) {
        this.sizeStr = sizeStr;
        initStr();
    }

    private void initStr() {
        Random r = new Random();
        String[] abc = {"a", "b", "c", " ", "d", "e", "f"};
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < sizeStr; i++) {
            sb.append(abc[r.nextInt(6)]);
        }
        this.str = sb.toString();
        System.out.printf("Count of words = %d%n%n", str.split("\\s+").length);
    }

    private Runnable rCompSp = () -> {
        long timeStart = System.currentTimeMillis();
        System.out.printf("Start of program for calculation spaces %n%n");
        int countSp = 0;
        char[] chars = this.str.toCharArray();
        for (char x: chars) {

            if(System.currentTimeMillis() - timeStart > 1000) {
                Thread.currentThread().interrupt();
            }

            if(Thread.currentThread().isInterrupted()) {
                System.out.printf("Thread for calculation spaces is interrupted, count_Sp_Intr = %d%n%n", countSp);
                break;
            }
            if( x == 32) {
                countSp++;
            }
        }
        System.out.printf("Thread for calculation spaces is finished, count_Sp = %d%n%n", countSp);
    };

    private Runnable rCompWrd = () -> {
        long timeStart = System.currentTimeMillis();
        System.out.printf("Start of program for calculation words %n%n");
        int countWrd = 0;
        char[] chars = this.str.toCharArray();
        boolean prevWrd = true;
        for(int i = 0; i < chars.length; i++) {

            if(System.currentTimeMillis() - timeStart > 1000) {
                Thread.currentThread().interrupt();
            }

            if(Thread.currentThread().isInterrupted()) {
                System.out.printf("Thread for calculation words is interrupted, count_Wrd_Intr = %d%n%n", countWrd);
                break;
            }

            if(chars[i] == 32) {
                countWrd = (prevWrd) ? ++countWrd : countWrd;
                prevWrd = false;
            } else {
                prevWrd = true;
            }

            if(i == chars.length - 1 && prevWrd) {
                countWrd++;
            }
        }
        System.out.printf("Thread for calculation words is finished, count_Wrd = %d%n%n", countWrd);
    };



    public Runnable getrCompSp() {
        return rCompSp;
    }

    public Runnable getrCompWrd() {
        return rCompWrd;
    }
}


