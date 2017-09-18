package ru.job4j.taskThreads;

import org.junit.Test;

public class TestWordsAndSpaces {
    @Test
    public void whenCalculateConcurrentThenCalculateDifferentSpeed() {
        WordsAndSpaces wordsAndSpaces = new WordsAndSpaces();
        wordsAndSpaces.computeWordsAndSpaces();
    }
}
