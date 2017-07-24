package ru.job4j.stocktaking;

import java.util.Arrays;
import java.util.Random;

/**
 * Class Class for store items.
 * @author atrifonov
 * @since 19.07.2017
 * @version 1
 */
public class Tracker {
    /**
     * Array of Item's.
     */
    private Item[] items = new Item[100];

    /**
     * Position of next Item. This item will be added in array items.
     */
    private int position = 0;

    /**
     * Object for generate Id of Item.
     */
    private static final Random RN = new Random();

    /**
     * Add item in items.
     * @param item added object.
     * @return added object or null if items is fill.
     */
    public Item add(Item item) {
        Item itemAdded = null;
        if (position < 100) {
            item.setId(generateId());
            this.items[position++] = item;
            itemAdded = item;
        }
        return itemAdded;
    }

    /**
     * Update item of items with same Id.
     * @param item new value of item with same Id.
     */
    public void update(Item item) {
        for (int i = 0; i < position; i++) {
            if (item != null && item.getId().equals(items[i].getId())) {
                items[i] = item;
                break;
            }
        }
    }

    /**
     * Delete item with same Id.
     * @param item deleted element.
     */
    public void delete(Item item) {
        int index = 0;
        boolean hasItem = false;
        for (int i = 0; i < position; i++) {
            if (item != null && item.getId().equals(items[i].getId())) {
                hasItem = true;
                index = i;
                items[i] = null;
                break;
            }
        }

        if (hasItem) {
            System.arraycopy(items, index + 1, items, index, position - index - 1);
            position--;
        }
    }

    /**
     * Find all not null elements.
     * @return array without null elements.
     */
    public Item[] findAll() {
        return Arrays.copyOf(items, position);
    }

    /**
     * Find all elements with same name.
     * @param key name of element.
     * @return array of elements with same name.
     */
    public Item[] findByName(String key) {
        int countItems = 0;
        Item[] sameName = new Item[items.length];
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                sameName[countItems++] = item;
            }
        }
        return Arrays.copyOf(sameName, countItems);
    }

    /**
     * Find element with same id.
     * @param id id of find element.
     * @return Item with same id or null if items not has element with same id.
     */
    protected Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
            }
        }
        return result;
    }

    /**
     * generate id for item.
     * @return Id for assign item.
     */
    String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
    }
}
