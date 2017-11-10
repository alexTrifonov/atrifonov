package ru.job4j.stocktaking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        assertThat(tracker.findById(item.getId()), is(item));
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
        Item itemUpdate = new Item("test4", "testDescription", item.getCreate());
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

        int oldSize = tracker.findAll().size();

        tracker.delete(item);
        Item nullItem = null;
        assertThat(tracker.findById(item.getId()), is(nullItem));
    }


    /**
     * Test findAll.
     */
    @Test
    public void whenTrackerHasOnlyNullItemsThenFindAllreturnListZeroSize() {
        Tracker tracker = new Tracker();
        List<Item> items = tracker.findAll();
        for(Item x : items) {
            tracker.delete(x);
        }
        assertThat(tracker.findAll().size(), is(0));
    }


    /**
     * Test findByName.
     */
    @Test
    public void whenTrackerHasSameNameItemsThenFindByNameReturnListItemsSameName() {
        Tracker tracker = new Tracker();
        List<Item> items = tracker.findAll();
        for(Item x : items) {
            tracker.delete(x);
        }
        Item item = new Item("test", "testDescription", 132L);
        Item item2 = new Item("test", "testDescription2", 144L);
        Item item3 = new Item("test3", "testDescription3", 168L);
        tracker.add(item);
        tracker.add(item2);
        tracker.add(item3);
        List<Item> expectedItems = new ArrayList<>();
        expectedItems.add(item);
        expectedItems.add(item2);
        List<Item> findItems = tracker.findByName(item.getName());
        assertThat(findItems.containsAll(expectedItems) && findItems.size() == expectedItems.size(), is(true));
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
    public void whenTrackerHasFillListThenNotWillAddNewItem() {
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