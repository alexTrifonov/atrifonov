package ru.job4j.taskIterator;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        boolean hasEven = false;
        if(position < array.length)   {
            hasEven = array[position] % 2 == 0;
        }

        while (!hasEven && position < array.length){
            position++;
            hasEven = array[position] % 2 == 0;
        }
        return hasEven;
    }

    @Override
    public Integer next() {
        if(hasNext()) {
            return array[position++];
        } else {
            throw new NoSuchElementException();
        }
    }
}
