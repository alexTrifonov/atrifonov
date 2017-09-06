package ru.job4j.taskMap;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 06.09.2017.
 * @version 1.
 */
public class TestSimpleMap {
    /**
     * Test insert, iterator.
     */
    @Test
    public void whenIsertAllItemThenIteratorReturnTheir() {
        SimpleMap<String, Integer> map = new SimpleMap<>(20);
        String[] abc = {"a","b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t"};
        List<String> list = new ArrayList<>();
        for(int i = 0; i< 20; i++) {
            map.insert(abc[i], i);
        }
        for(String key : map){
            list.add(key);
        }

        String[] expect = {"d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "a", "b", "c"};
        List<String> listExpected = new ArrayList<>(Arrays.asList(expect));
        assertThat(list, is(listExpected));
    }

    /**
     * Test insert, get.
     */
    @Test
    public void whenInsertItemThenGetSameItem() {
        SimpleMap<String, Integer> map = new SimpleMap<>(20);
        map.insert("f", 11);
        assertThat(map.get("f"), is(11));
    }

    /**
     * Test insert, get.
     */
    @Test
    public void whenInsertItemThenDeletSameItem() {
        SimpleMap<String, Integer> map = new SimpleMap<>(20);
        map.insert("f", 11);
        map.delete("f");
        Integer numb = map.get("f");
        map.insert("f", 22);
        boolean hasNew = numb == null && map.get("f") == 22;
        assertThat(hasNew, is(true));
    }

    /**
     * Test insert, get.
     */
    @Test
    public void whenInsertItemAndTryInsetSameKeyThenFalse() {
        SimpleMap<String, Integer> map = new SimpleMap<>(20);
        map.insert("f", 11);
        boolean tryInsert =  map.insert("f", 22);
        boolean hasNew = !tryInsert && map.get("f") == 11;
        assertThat(hasNew, is(true));
    }
}
