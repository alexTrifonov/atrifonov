package ru.job4j.taskSynchronize;

import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TestParallelSearch {
    @Test
    public void whenGetListFilesThenPrintHim() {
        List<String> exts = new LinkedList<>();
        exts.add("txt");
        exts.add("xml");
        String root = System.getProperty("user.dir");
        ParallelSearch search = new ParallelSearch(root, "mod", exts);
        List<String> strings = search.result();
        strings.forEach(value -> System.out.println(value));
    }

}
