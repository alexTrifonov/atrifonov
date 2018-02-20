package ru.job4j.simpleIO;

import org.junit.Test;

import javax.imageio.IIOException;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test SortBigFile.
 * @author atrifonov.
 * @version 1.
 * @since 19.02.2018.
 */
public class TestSortBigFile {
    /**
     * Test sort.
     */
    @Test
    public void whenNotSortedLinesInFileThenSortedLinesAnotherFile() {
        File source = new File("src\\main\\resources\\ru\\job4j\\simpleIO\\one.txt");
        File dest = new File("src\\main\\resources\\ru\\job4j\\simpleIO\\dest.txt");
        boolean sort = check(source, dest, false);
        assertThat(sort, is(true));
    }

    /**
     * Test sortOld.
     */
    @Test
    public void whenFileAreNotSortedThenGetSortedFile() {
        File source = new File("src\\main\\resources\\ru\\job4j\\simpleIO\\one.txt");
        File dest = new File("src\\main\\resources\\ru\\job4j\\simpleIO\\dest.txt");
        boolean sort = check(source, dest, true);
        assertThat(sort, is(true));
    }

    private boolean check(File source, File dest, boolean old) {
        boolean sort = true;
        SortBigFile sortBigFile = new SortBigFile();
        try {
            if (old) {
                sortBigFile.sortOld(source, dest);
            } else {
                sortBigFile.sort(source, dest);
            }
            sortBigFile.sort(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dest), "UTF-8"));
            String line = "";
            List<String> list = new ArrayList<>(6);
            while ( (line = br.readLine()) != null) {
                list.add(line);
            }
            br.close();
            int i = 1;
            for (String x : list) {
                if (!x.startsWith("" + i + "")) {
                    sort = false;
                    break;
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sort;
    }
}
