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
 * @since 24.07.2017.
 * @version 1.
 */
public class StubInputTest {

    /**
     * Test add.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
    }

    /**
     * Test update.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item());
        Input input = new StubInput(new String[]{"2", item.getId(), "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name"));
    }

    /**
     * Test delete.
     */
    @Test
    public void whenDeleteItemThenTrackerHasMinusOneItem() {
        Tracker tracker = new  Tracker();
        Item item = new Item();
        tracker.add(item);
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().size(), is(0));
    }

    /**
     * Test findById.
     */
    @Test
    public void whenFindItemByIdThenReturnSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item();
        tracker.add(item);
        Input input = new StubInput(new String[]{"4", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()), is(item));
    }

    /**
     * Test findByName.
     */
    @Test
    public void whenFindItemsByNameThenReturnListSameNameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test", "testDescription",123L);
        Item item1 = new Item("abc", "123", 123L);
        Item item2 = new Item("test", "description", 1500785132L);
        tracker.add(item);
        tracker.add(item1);
        tracker.add(item2);
        Input input = new StubInput(new String[]{"5", "test", "6"});
        new StartUI(input, tracker).init();
        List<Item> expectedItem = new ArrayList<>(2);
        expectedItem.add(item);
        expectedItem.add(item2);
        assertThat(tracker.findByName("test"), is(expectedItem));
    }

    /**
     * Test findAll.
     */
    @Test
    public void whenAddTwoItemThenReturnListTwoItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test", "testDescription",123L);
        Item item2 = new Item("test", "description", 1500785132L);
        tracker.add(item);
        tracker.add(item2);
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        List<Item> expectedItem = new ArrayList<>(2);
        expectedItem.add(item);
        expectedItem.add(item2);
        assertThat(tracker.findAll(), is(expectedItem));
    }
}
