package ru.job4j.taskIterator;

import java.util.Iterator;

/**
 * Class for iterator for two-dimensional array.
 * @author atrifonov.
 * @since 16.08.2017.
 * @version 1.
 */
public class TwoDimensionalArrIterator implements Iterator<Integer> {
    /**
     * Internal array for store data.
     */
    private final int[][] array;

    /**
     * index of column.
     */
    private int positionY = 0;
    /**
     * index of row.
     */
    private int positionX = 0;

    /**
     * Construct TwoDimensionalArrIterator
     * @param array array that need iterator.
     */
    public TwoDimensionalArrIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return positionY + positionX < array.length + array[0].length - 1;
    }

    @Override
    public Integer next() {
        if (positionX == array[0].length) {
            positionY++;
            positionX = 0;
        }
        return array[positionY][positionX++];
    }
}
