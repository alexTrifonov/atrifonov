package ru.job4j.finishTask;

import java.util.*;

/**
 * Class for check rearrangement char in two words.
 * @author atrifonov.
 * @version 1.
 * @since 11.10.2017.
 */
public class RearrangementChar {

    /**
     * Compute are word two rearrangement chars of word one.
     * @param one word one.
     * @param two wore two.
     * @return true, if word two are rearrangement chars of word one.
     */
    public boolean check(String one, String two) {
        boolean yes = false;

        if(one.length() != two.length()) {
            yes = false;
        } else {
            Map<Character, Integer> mapOne = createMap(one);
            Map<Character, Integer> mapTwo = createMap(two);
            yes = mapOne.equals(mapTwo);

        }


        return yes;
    }

    /**
     * Create Map<Character, Integer> from word chars, key - char, value - count this char in word.
     * @param str word for create map.
     * @return map<Character, Integer>.
     */
    private Map<Character, Integer> createMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        char[] charsOne = str.toCharArray();

        for(char ch : charsOne) {
            if( map.computeIfPresent(ch, (k, v) -> v + 1) == null) {
                map.putIfAbsent(ch, 1);
            }
        }

        return map;
    }
}
