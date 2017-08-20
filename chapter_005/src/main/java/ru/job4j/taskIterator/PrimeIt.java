package ru.job4j.taskIterator;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for iterator for prime number in array.
 * @author atrifonov.
 * @since 16.08.2017.
 * @version 1.
 */
public class PrimeIt implements Iterator<Integer> {
    /**
     * Internal array for store data.
     */
    private final int[] array;
    /**
     * Index.
     */
    private int position = 0;

    /**
     * Construct PrimeIt.
     * @param array array for iterator.
     */
    public PrimeIt(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean hasEven = false;
        if(position < array.length)   {
            hasEven = isPrime(array[position]);
        }

        while (!hasEven && position < array.length){
            position++;
            hasEven = isPrime(array[position]);
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

    private boolean isPrime(int number) {
        boolean isPrime = true;
        if(number > 1) {
            for (int i = 2; i < number; i++) {
                if(number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        } else {
            isPrime = false;
        }
        return isPrime;
    }


}
