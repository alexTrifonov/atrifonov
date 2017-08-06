package ru.job4j.stocktaking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private List<Item> items = new ArrayList<>(100);


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
        if (items.size() < 100) {
            item.setId(generateId());
            items.add(item);
            itemAdded = item;
        }
        return itemAdded;
    }

    /**
     * Update item of items with same Id.
     * @param item new value of item with same Id.
     */
    public void update(Item item) {
        for (int i = 0; i < items.size(); i++) {
            if (item != null && item.getId().equals(items.get(i).getId())) {
                long timeCreate = items.get(i).getCreate();
                item.setCreate(timeCreate);
                items.set(i, item);
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
        for (Item x : items) {
            if (item != null && item.getId().equals(x.getId())) {
                items.remove(x);
                break;
            }
        }
    }

    /**
     * Find all not null elements.
     * @return array without null elements.
     */
    public List<Item> findAll() {
        return new ArrayList<>(items);
    }

    /**
     * Find all elements with same name.
     * @param key name of element.
     * @return array of elements with same name.
     */
    public List<Item> findByName(String key) {
        ArrayList<Item> sameName = new ArrayList<>(items.size());
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                sameName.add(item);
            }
        }
        return sameName;
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
