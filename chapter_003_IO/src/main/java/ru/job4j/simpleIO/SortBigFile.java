package ru.job4j.simpleIO;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Class for sort big file.
 * @author atrifonov.
 * @version 1.
 * @since 19.02.2018.
 */
public class SortBigFile {

    /**
     * Read all lines from file source. Sort lines. Write lines to dest.
     * @param source file source
     * @param dest file destination.
     * @throws IOException IOException.
     */
    public void sort(File source, File dest) throws IOException{
        List<String> lines = Files.readAllLines(source.toPath());
        Collections.sort(lines, (first, second) -> first.length() - second.length());
        RandomAccessFile raf = new RandomAccessFile(dest, "rw");
        long countBytes = 0;
        String separator = System.getProperty("line.separator");
        for(String x : lines) {
            if (!x.isEmpty()) {
                String str = String.format("%s%s", x, separator);
                countBytes += str.getBytes().length;
                raf.write(str.getBytes("UTF-8"));
                raf.seek(countBytes);
            }
        }
        raf.close();
    }
    /**
     * Read all bytes from file source. Make lines. Sort lines. Write lines to dest.
     * @param source file source
     * @param dest file destination.
     * @throws IOException IOException.
     */
    public void sortOld(File source, File dest) throws IOException {
        RandomAccessFile srcRaf = new RandomAccessFile(source, "r");
        RandomAccessFile destRaf = new RandomAccessFile(dest, "rw");
        byte[] bytes = new byte[1000000];
        StringBuilder sb = new StringBuilder();
        ByteArrayOutputStream out;
        int countRead = 0;
        while ( (countRead = srcRaf.read(bytes, 0, bytes.length)) > 0) {
            out = new ByteArrayOutputStream(countRead);
            sb.append(new String(out.toByteArray(), "UTF-8"));
        }
        String separator = System.getProperty("line.separator");
        String[] strings = sb.toString().split(separator);
        Arrays.sort(strings, (first, second) -> first.length() - second.length());
        long countBytes = 0;
        for (String x : strings) {
            String str = String.format("%s%s", x, separator);
            countBytes += str.getBytes().length;
            destRaf.write(str.getBytes("UTF-8"));
            destRaf.seek(countBytes);
        }
    }
}
