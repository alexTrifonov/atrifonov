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
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Class for parallel search TEXT in all files.
 * @author atrifonov.
 * @since 02.10.2017.
 * @version 1.
 */
public class ParallelSearch {
    /**
     * The ROOT directory for search files.
     */
    private final String ROOT;
    /**
     * File contain or not contain this TEXT.
     */
    private final String TEXT;
    /**
     * The list of extensions of files. Files with this extensions can contains TEXT.
     */
    private final List<String> EXTS;
    /**
     * List for store of file names with assigned TEXT.
     */
    //private final List<String> LIST_FILES = new LinkedList<>();
    private final ConcurrentLinkedQueue<String> LIST_FILES = new ConcurrentLinkedQueue<>();
    private static volatile int countThread = 0;
    /**
     * Construct ParallelSearch object with ROOT, TEXT and EXTS.
     * @param root ROOT directory.
     * @param text TEXT for find.
     * @param exts extensions of files.
     */
    public ParallelSearch(String root, String text, List<String> exts) {
        this.ROOT = root;
        this.TEXT = text;
        this.EXTS = exts;
    }

    public List<String> result() {
        addListFiles(ROOT);

        boolean allThrFinish = false;
        while (!allThrFinish) {
            if(countThread == 0) {
                allThrFinish = true;
            }
        }
        return new LinkedList<>(this.LIST_FILES);
    }


    private void addListFiles(String root) {
        File fileRoot = new File(root);
        if(!fileRoot.isDirectory()) {
            boolean eq = false;
            for(String x : this.EXTS) {
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
                has = line.contains(this.TEXT);
            }

            if(check && !has) {
                while ((line = reader.readLine()) != null) {
                    if(line.contains(this.TEXT)) {
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

            if(hasText(file)) {
                LIST_FILES.add(file.getAbsolutePath());
            }
            countThread--;
        };
        Thread t = new Thread(r);

        t.start();
    }
}
