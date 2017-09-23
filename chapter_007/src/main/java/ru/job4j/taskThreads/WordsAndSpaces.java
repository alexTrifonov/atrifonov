package ru.job4j.taskThreads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WordsAndSpaces {
    private StringBuilder sb;

    public WordsAndSpaces() {
        initial();
    }

    public void computeWordsAndSpaces() {
        try {
            Thread tOne = new Thread(new ComputeWords(this.sb));
            Thread tTwo = new Thread(new ComputeSpaces(this.sb));
            tOne.start();
            tTwo.start();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initial() {
        try {
            this.sb = new StringBuilder();
            Scanner in = new Scanner(Paths.get("WordsAndSpaces.txt"), "UTF-8");
            while (in.hasNext()){
                this.sb.append(in.next()).append(" ");
            }
            System.out.println("sb = " + this.sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

