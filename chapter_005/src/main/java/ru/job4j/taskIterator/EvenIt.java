package ru.job4j.taskIterator;
import java.util.Iterator;

/**
 * Class for iterator for even number in array.
 * @author atrifonov.
 * @since 16.08.2017.
 * @version 1.
 */
public class EvenIt implements Iterator<Integer> {
    /**
     * Internal array for store data.
     */
    private final int[] array;
    /**
     * Index.
     */
    private int position = 0;

    /**
     * Construct EvenIt.
     * @param array array for iterator.
     */
    public EvenIt(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        int i = position;
        boolean hasEven = false;
        while (!hasEven && i < array.length) {
            hasEven = (array[i++] % 2 == 0);
        }
        return hasEven;
    }

    @Override
    public Integer next() {
        int item;
        if(array[position] % 2 == 0) {
            item = array[position++];
        } else {
            while (array[position] % 2 != 0){
                position++;
            }
            item = array[position++];
        }

        return item;
    }
}
