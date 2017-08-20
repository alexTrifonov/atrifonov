package ru.job4j.taskIterator;

import java.util.*;

/**
 * Class for iterator for prime number in array.
 * @author atrifonov.
 * @since 19.08.2017.
 * @version 1.
 */
public class Converter {
    /**
     * Convert iterator of iterators integers to iterator of integers.
     * @param it iterator of iterators integers
     * @return iterator of integers
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it){
        return new Iterator<Integer>() {
            private Iterator<Integer> iterator;
            @Override
            public boolean hasNext() {

                boolean has = false;
                if(iterator == null) {
                    iterator = it.next();
                }
                if(iterator.hasNext()) {
                    has = true;
                } else if(it.hasNext()) {
                    iterator = it.next();
                    has = true;
                }
                return has;
            }

            @Override
            public Integer next() {
                if(iterator == null) {
                    iterator = it.next();
                }

                if(hasNext()) {
                    return iterator.next();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

}

