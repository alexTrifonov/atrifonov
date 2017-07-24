package ru.job4j.stocktaking;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author atrifonov.
 * @since 19.07.2017.
 * @version 1.
 */
public class TrackerTest {


    /**
     * Test add.
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 132L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }


    /**
     * Test update.
     */
    @Test
    public void whenUpdateItemThenTrackerHasAnotherItemWithSameId() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 132L);
        Item item2 = new Item("test2", "testDescription2", 144L);
        Item item3 = new Item("test3", "testDescription3", 168L);
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        String id = item.getId();
        Item itemUpdate = new Item("test4", "testDescription", 100L);
        itemUpdate.setId(id);
        tracker.update(itemUpdate);
        assertThat(tracker.findById(id), is(itemUpdate));
    }


    /**
     * Test delete.
     */
    @Test
    public void whenDeleteItemThenTrackerHasMinusOneItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 132L);
        Item item2 = new Item("test2", "testDescription2", 144L);
        Item item3 = new Item("test3", "testDescription3", 168L);
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        tracker.delete(item);
        assertThat(tracker.findByName(item.getName()).length, is(0));
    }


    /**
     * Test findAll.
     */
    @Test
    public void whenTrackerHasOnlyNullItemsThenFindAllreturnArrayZeroLength() {
        Tracker tracker = new Tracker();
        assertThat(tracker.findAll().length, is(0));
    }


    /**
     * Test findByName.
     */
    @Test
    public void whenTrackerHasSameNameItemsThenFindByNameReturnArrayItemsSameName() {
        Tracker tracker = new Tracker();
        Item item = new Item("test", "testDescription", 132L);
        Item item2 = new Item("test", "testDescription2", 144L);
        Item item3 = new Item("test3", "testDescription3", 168L);
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        Item[] items = {item, item2};
        assertThat(tracker.findByName(item.getName()), is(items));
    }


    /**
     * Test findById.
     */
    @Test
    public void whenTrackerNoHasItemWhithThisIdThenReturnNull() {
        Tracker tracker = new Tracker();
        Item item = new Item("test", "testDescription", 132L);
        Item item2 = new Item("test", "testDescription2", 144L);
        Item item3 = new Item("test3", "testDescription3", 168L);
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        String id = "0";
        Item item1 = null;
        assertThat(tracker.findById(id), is(item1));
    }


    /**
     * Test add.
     */
    @Test
    public void whenTrackerHasFillArrayThenNotWillAddNewItem() {
        Tracker tracker = new Tracker();
        for (int i = 0; i < 100; i++) {
            String name = "name" + i;
            String description = "description" + i;
            Long create = i + 1L;
            tracker.add(new Item(name, description, create));
        }
        Item item = new Item("test", "testDescription", 133L);
        Item item1 = null;
        assertThat(tracker.add(item), is(item1));
    }
}