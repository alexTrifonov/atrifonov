package ru.job4j.taskSet;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Iterator;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 02.09.2017.
 * @version 1.
 */
public class TestLinkedSet {
    /**
     * Test remove.
     */
    @Test
    public void whenDeleteFirstItemThenSetHasNotFirstItem() {
        LinkedSet<String> set = new LinkedSet<>();
        set.add("one");
        set.add("two");
        set.add("three");

        Iterator<String> it = set.iterator();

        boolean hasOne = false;
        while (it.hasNext()) {
            if(it.next().equals("one")) {
                hasOne = true;
                break;
            }
        }

        it = set.iterator();
        it.next();
        it.remove();

        it = set.iterator();
        boolean hasNotOne = true;
        while (it.hasNext()) {
            if(it.next().equals("one")) {
                hasNotOne = false;
            }
        }
        assertThat(hasOne && hasNotOne, is(true));
    }

    /**
     * Test add duplicate.
     */
    @Test
    public void whenAddDuplicateThenDuplicateDoesNotAdd() {
        LinkedSet<String> set = new LinkedSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("two");

        int countTwo = 0;
        for(String x : set) {
            if(x.equals("two")){
                countTwo++;
            }
        }

        assertThat(countTwo, is(1));
    }

}
