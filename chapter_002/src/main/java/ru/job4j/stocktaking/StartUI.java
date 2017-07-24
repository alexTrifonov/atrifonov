package ru.job4j.stocktaking;

import java.util.Date;

/**
 * Class Main class of application StockTaking.
 * @author atrifonov
 * @since 20.07.2017
 * @version 1
 */
public class StartUI {

    /**
     * Const for menu "Add new item".
     */
    private static final String ADD_NEW_ITEM = "0";

    /**
     * Const for menu "Show all items".
     */
    private static final String SHOW_ALL_ITEMS = "1";

    /**
     * Const for menu "Edit item".
     */
    private static final String EDIT_ITEM = "2";

    /**
     * Const for menu "Delete item".
     */
    private static final String DELETE_ITEM = "3";

    /**
     * Const for menu "Find item by Id".
     */
    private static final String FIND_ITEM_BY_ID = "4";

    /**
     * Const for menu "Find items by name".
     */
    private static final String FIND_ITEMS_BY_NAME = "5";

    /**
     * Const for menu exit.
     */
    private static final String EXIT = "6";

    /**
     * Menu of application.
     */
    private String[] menu = {"0. Add new item", "1. Show all items", "2. Edit item", "3. Delete item", "4. Find item by Id",
            "5. Find items by name", "6. Exit Program"};

    /**
     * Object for execute user input.
     */
    private Input input;

    /**
     * Object for store items.
     */
    private Tracker tracker;

    /**
     * Construct object StartUI with object input.
     * @param input Object for execute input.
     */
    public StartUI(Input input) {
        this.input = input;
        this.tracker = new Tracker();
    }

    /**
     * Construct object StartUI with object input and object tracker.
     * @param input Object for execute input.
     * @param tracker Object for store items.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Main work of application.
     */
    private void init() {
        Tracker tracker = new Tracker();
        String action = "";
        while (!EXIT.equals(action)) {
            if (ADD_NEW_ITEM.equals(action)) {
                tracker.add(createItem());
            } else if (SHOW_ALL_ITEMS.equals(action)) {
                showAllItems(tracker);
            } else if (EDIT_ITEM.equals(action)) {
                String id = input.ask("Enter Id:");
                tracker.update(createItem(id, false));
            } else if (DELETE_ITEM.equals(action)) {
                String id = input.ask("Enter Id:");
                tracker.delete(createItem(id, true));
            } else if (FIND_ITEM_BY_ID.equals(action)) {
                findItemById(tracker);
            } else if (FIND_ITEMS_BY_NAME.equals(action)) {
                findItemsByName(tracker);
            }

            action = showMenuAndReturnAction();
        }
    }

    /**
     * Entry point.
     * @param args Args for execute application.
     */
    public static void main(String[] args) {
        ConsoleInput consoleInput = new ConsoleInput();
        StartUI startUI = new StartUI(consoleInput);
        startUI.init();
    }

    /**
     * Print all items from passed array.
     * @param items Array of items.
     */
    private void printItems(Item[] items) {
        for (Item x : items) {
            System.out.println(itemToString(x));
        }
    }

    /**
     * Return item to string.
     * @param item Item for get string.
     * @return String from item.
     */
    private String itemToString(Item item) {
        StringBuilder sb = new StringBuilder();
        sb.append("\"Name : \" ");
        sb.append(item.getName());
        sb.append(" \"Description : \" ");
        sb.append(item.getDescription());
        sb.append(" \"create : \" ");
        sb.append(new Date(item.getCreate()));
        sb.append(" \"Id : \" ");
        sb.append(item.getId());
        return sb.toString();
    }

    /**
     * Create item with name and description.
     * @return Created item.
     */
    private Item createItem() {
        String name =  input.ask("Enter name :");
        String description = input.ask("Enter description :");
        long create = System.currentTimeMillis();
        return new Item(name, description, create);
    }

    /**
     * Create item with name and description.
     * @param id id of item
     * @param isEmptyItem True for create empty item.
     * @return Created item.
     */
    private Item createItem(String id, boolean isEmptyItem) {
        Item item;
        if (isEmptyItem) {
            item = new Item("", "", 0L);
            item.setId(id);
        } else {
            item = createItem();
            item.setId(id);
        }
        return item;
    }

    /**
     * Show all not null elements.
     * @param tracker Tracker with items.
     */
    private void showAllItems(Tracker tracker) {
        Item[] itemsAll = tracker.findAll();
        if (itemsAll.length != 0) {
            printItems(itemsAll);
        } else {
            System.out.println("Array items is empty");
        }
    }

    /**
     * Show element with same id.
     * @param tracker Tracker with items.
     */
    private void findItemById(Tracker tracker) {
        String id = input.ask("Enter Id:");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println(itemToString(item));
        } else {
            System.out.println("Isn't item with id = " + id);
        }
    }

    /**
     * Show menu.
     * @return Action from user.
     */
    private String showMenuAndReturnAction() {
        System.out.println();
        for (String x : menu) {
            System.out.println(x);
        }

        return input.ask("Select number. Enter number from 0 to 6");
    }

    /**
     * Show all elements with same name.
     * @param tracker Tracker with items.
     */
    private void findItemsByName(Tracker tracker) {
        String name =  input.ask("Enter name :");
        Item[] itemsWithSameName = tracker.findByName(name);
        if (itemsWithSameName.length != 0) {
            printItems(tracker.findByName(name));
        } else {
            System.out.println("Are not item with name = " + name);
        }

    }
}
