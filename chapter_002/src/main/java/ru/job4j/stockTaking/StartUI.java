package ru.job4j.stockTaking;

import java.util.Date;

/**
 * Class Main class of application StockTaking.
 * @author atrifonov
 * @since 20.07.2017
 * @version 1
 */
public class StartUI {
    /*
     * Menu of application.
     */
    private String[] menu = {"0. Add new item", "1. Show all items", "2. Edit item", "3. Delete item", "4. Find item by Id",
            "5. Find items by name", "6. Exit Program"};

    /*
     * Object for execute user input.
     */
    private Input input;

    /**
     * Construct object StartUI with object input.
     * @param input Object for execute input.
     */
    private StartUI(Input input) {
        this.input = input;
    }

    /**
     * Main work of application.
     */
    private void init() {
        Tracker tracker = new Tracker();
        String action = "";
        while (!action.equals("6")) {
            if (action.equals("0")) {
                tracker.add(createItem());
            } else if(action.equals("1")) {
                showAllItems(tracker);
            } else if (action.equals("2")) {
                String id = input.ask("Enter Id:");
                tracker.update(createItem(id, false));
            } else if (action.equals("3")) {
                String id = input.ask("Enter Id:");
                tracker.delete(createItem(id, true));
            } else if (action.equals("4")) {
                findItemById(tracker);
            } else if (action.equals("5")) {
                findItemsByName(tracker);
            }

            action = showMenuAndReturnAction();
        }
    }

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
     * @param Id id of item
     * @param isEmptyItem True for create empty item.
     * @return Created item.
     */
    private Item createItem(String Id, boolean isEmptyItem) {
        Item item;
        if (isEmptyItem) {
            item = new Item("", "", 0L);
            item.setId(Id);
        } else {
            item = createItem();
            item.setId(Id);
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
