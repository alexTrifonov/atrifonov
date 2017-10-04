package ru.job4j.taskSynchronize;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for parallel search text in all files.
 * @author atrifonov.
 * @since 02.10.2017.
 * @version 1.
 */
public class ParallelSearch {
    /**
     * The root directory for search files.
     */
    private String root;
    /**
     * File contain or not contain this text.
     */
    private String text;
    /**
     * The list of extensions of files. Files with this extensions can contains text.
     */
    List<String> exts;
    /**
     * List for store of file names with assigned text.
     */
    private final List<String> listFiles = new LinkedList<>();

    private static volatile int countThread = 0;
    /**
     * Construct ParallelSearch object with root, text and exts.
     * @param root root directory.
     * @param text text for find.
     * @param exts extensions of files.
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
    }

    public List<String> result() {
        addListFiles(root);

        boolean allThrFinish = false;
        while (!allThrFinish) {
            if(countThread == 0) {
                allThrFinish = true;
            }
        }
        return this.listFiles;
    }


    private void addListFiles(String root) {
        File fileRoot = new File(root);
        if(!fileRoot.isDirectory()) {
            boolean eq = false;
            for(String x : this.exts) {
                if(fileRoot.getName().endsWith(x)) {
                    eq = true;
                    break;
                }
            }
            if(eq) {
                activateThread(fileRoot);
            }
        } else {
            FilenameFilter filter = (File dir, String name) -> {
                return !(new File(dir.getAbsoluteFile() + "\\" + name).isHidden());
            };
            String[] allFiles = fileRoot.list(filter);
            for(String x : allFiles) {
                if(fileRoot.getPath().endsWith("\\")) {
                    addListFiles(fileRoot.getAbsolutePath() + x);
                } else {
                    addListFiles(fileRoot.getAbsolutePath() + "\\" + x);
                }

            }
        }



    }

    private boolean hasText(File file)  {
        Path path = file.toPath();
        boolean has = false;
        try {
            boolean check = true;
            BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
            String line = "";
            try {
                line = reader.readLine();
            } catch (MalformedInputException e) {
                check = false;
            }
            if(line != null) {
                has = line.contains(this.text);
            }

            if(check && !has) {
                while ((line = reader.readLine()) != null) {
                    if(line.contains(this.text)) {
                        has = true;
                        break;
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return has;
    }

    private void activateThread(File file) {
        Runnable r = () -> {
            countThread++;
            boolean eq = false;

            if(hasText(file)) {
                synchronized (this.listFiles) {
                    listFiles.add(file.getAbsolutePath());
                }
            }
            countThread--;
        };
        Thread t = new Thread(r);

        t.start();
    }
}
