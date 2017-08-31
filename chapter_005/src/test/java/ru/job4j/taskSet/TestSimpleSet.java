package ru.job4j.taskSet;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Test
 *
 * @author atrifonov.
 * @since 31.08.2017.
 * @version 1.
 */
public class TestSimpleSet {
    /**
     * Test remove.
     */
    @Test
    public void whenAddThreeItemAndRemoveSecondItemThenGetOnlyThirdItem() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("three");

        Iterator<String> it = set.iterator();
        it.next();
        it.next();
        it.remove();
        String a = it.next();
        boolean aAndFalse = a.equals("three") && !it.hasNext();
        assertThat(aAndFalse, is(true));
    }

    /**
     * Test remove.
     */
    @Test
    public void whenRemoveSecondAndAddAnotherItemThenGetTwoItem() {
        SimpleSet<String> set = new SimpleSet<>(4);
        set.add("one");
        set.add("two");
        set.add("three");

        Iterator<String> it = set.iterator();
        it.next();
        it.next();
        it.remove();
        set.add("four");
        String a = it.next();
        String b = it.next();
        boolean aAndBAndFalse = a.equals("three") && b.equals("four") && !it.hasNext();
        assertThat(aAndBAndFalse, is(true));
    }

    /**
     * Test add same item.
     */
    @Test
    public void whenAddSameItemThenThisItemDoesNotAdd() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("one");

        String item;
        int countSame= 0;
        for (String st: set) {
            item = st;
            if(item.equals("one")) {
                countSame++;
            }
        }
        assertThat(countSame, is(1));
    }

    /**
     * Test remove.
     */
    @Test
    public void whenAddThreeItemAndRemoveFirstItemThenGetOnlyThirdItem() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("three");

        Iterator<String> it = set.iterator();
        it.next();
        it.remove();

        it = set.iterator();
        String a = it.next();
        String b = it.next();
        boolean aAndBAndFalse = a.equals("two") && b.equals("three") && !it.hasNext();
        assertThat(aAndBAndFalse, is(true));
    }

    /**
     * Test remove.
     */
    @Test
    public void whenAddThreeItemAndRemoveThirdItemThenSetHaveTwoItem() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("three");

        Iterator<String> it = set.iterator();
        it.next();
        it.next();
        it.next();
        it.remove();
        it = set.iterator();
        String a = it.next();
        String b = it.next();
        boolean aAndBAndFalse = a.equals("one") && b.equals("two") && !it.hasNext();
        assertThat(aAndBAndFalse, is(true));
    }
}
