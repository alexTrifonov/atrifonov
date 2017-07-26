package ru.job4j.stocktaking;

import java.util.Arrays;

/**
 * Class for menu of actions on the items in Tracker.
 * @author atrifonov
 * @since 25.07.2017
 * @version 1
 */
public class MenuTracker {
    /**
     * Object for execute user input.
     */
    private Input input;

    /**
     * Object for store items.
     */
    private Tracker tracker;

    /**
     * Set of actions.
     */
    private UserAction[] actions = new UserAction[7];

    /**
     * Construct object MenuTracker with input and tracker.
     * @param input Object for execute user input.
     * @param tracker Object for store items.
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Fill set of actions.
     */
    public void fillActions() {
        this.actions[0] = this.new AddItem("Add the new item", 0);
        this.actions[1] = this.new ShowAllItems("Show all items", 1);
        this.actions[2] = this.new EditItem("Edit item", 2);
        this.actions[3] = new MenuTracker.DeleteItem("Delete item", 3);
        this.actions[4] = new MenuTracker.FindItemById("Find item by Id", 4);
        this.actions[5] = new FindItemsByName("Find item by name", 5);
        this.actions[6] = new ExitProgram("Exit Program", 6);
    }

    /**
     * For get array of action numbers.
     * @return array numbers. Every number correspond exist action.
     */
    public int[] getArrayNumbersAction() {
        int[] arrayNumbersAction = new int[actions.length];
        int i = 0;
        for(UserAction x : actions) {
            if(x != null) {
                arrayNumbersAction[i++] = x.key();
            }
        }
        return Arrays.copyOf(arrayNumbersAction, i);
    }

    /**
     * Execute specified action.
     * @param key Number of action.
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Print set of actions.
     */
    public void show() {
        for(UserAction action : this.actions) {
            if(action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Class for add item in Tracker.
     * @author atrifonov
     * @since 24.07.2017
     * @version 1
     */
    private class AddItem extends BaseAction {

        public AddItem(String name, int key) {
            super(name, key);
        }
        @Override
        public int key() {
            return getKey();
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, enter the item's name: ");
            String description = input.ask("Please, enter the item's description: ");
            tracker.add(new Item(name, description, System.currentTimeMillis()));
        }

    }

    /**
     * Class for show all not null items in Tracker.
     * @author atrifonov
     * @since 25.07.2017
     * @version 1
     */
    private class ShowAllItems extends BaseAction {

        public ShowAllItems(String name, int key){
            super(name, key);
        }

        @Override
        public int key() {
            return getKey();
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] itemsAll = tracker.findAll();
            if (itemsAll.length != 0) {
                for (Item x : itemsAll) {
                    System.out.println(x);
                }
            } else {
                System.out.println("Array items is empty");
            }
        }
    }

    /**
     * Class for edit the item in Tracker.
     * @author atrifonov
     * @since 25.07.2017
     * @version 1
     */
    private class EditItem extends BaseAction {

        public EditItem(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return getKey();
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter the item's id:");
            String name =  input.ask("Please, enter the new item's name: ");
            String description = input.ask("Please, enter the new item's description: ");
            Item itemFresh = new Item(name, description, 123L);
            itemFresh.setId(id);
            tracker.update(itemFresh);
        }


    }

    /**
     * Class for delete the item in Tracker.
     * @author atrifonov
     * @since 25.07.2017
     * @version 1
     */
    private static class DeleteItem extends BaseAction{

        public DeleteItem(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return getKey();
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter the item's Id:");
            Item item = new Item("", "", 123L);
            item.setId(id);
            tracker.delete(item);
        }
    }

    /**
     * Class for find the item by Id in Tracker.
     * @author atrifonov
     * @since 25.07.2017
     * @version 1
     */
    private static class FindItemById extends BaseAction {

        public FindItemById(String name, int key) {
            super(name, key);
        }

        @Override
        public int key() {
            return getKey();
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter the item's Id:");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(item);
            } else {
                System.out.println("Isn't item with id = " + id);
            }
        }
    }

}

/**
 * Class for find the item by name in Tracker.
 * @author atrifonov
 * @since 25.07.2017
 * @version 1
 */
class FindItemsByName extends BaseAction {

    public FindItemsByName(String name, int key) {
        super(name, key);
    }

    @Override
    public int key() {
        return getKey();
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String name =  input.ask("Enter name :");
        Item[] itemsWithSameName = tracker.findByName(name);
        if (itemsWithSameName.length != 0) {
            for (Item x : itemsWithSameName) {
                System.out.println(x);
            }
        } else {
            System.out.println("Are not item with name = " + name);
        }
    }
}

/**
 * Class for exit from Program.
 * @author atrifonov
 * @since 25.07.2017
 * @version 1
 */
class ExitProgram extends BaseAction {

    public ExitProgram(String name, int key) {
        super(name, key);
    }

    @Override
    public int key() {
        return getKey();
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("Exit");
    }
}