package ru.job4j.taskThreads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
            File file = new File("E:\\Java\\PetrArsentev\\chapter_007\\1.Threads\\WordsAndSpaces.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String st;
            this.sb = new StringBuilder();
            while ((st = reader.readLine()) != null){
                if(!st.isEmpty()) {
                    this.sb.append(st).append(" ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

