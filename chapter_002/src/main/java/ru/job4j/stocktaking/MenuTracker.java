package ru.job4j.stocktaking;

import java.util.Date;

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
        this.actions[0] = this.new AddItem();
        this.actions[1] = this.new ShowAllItems();
        this.actions[2] = this.new EditItem();
        this.actions[3] = new MenuTracker.DeleteItem();
        this.actions[4] = new MenuTracker.FindItemById();
        this.actions[5] = new FindItemsByName();
        this.actions[6] = new ExitProgram();
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
    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please, enter the item's name: ");
            String description = input.ask("Please, enter the item's description: ");
            tracker.add(new Item(name, description, System.currentTimeMillis()));
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add the new item.");
        }
    }

    /**
     * Class for show all not null items in Tracker.
     * @author atrifonov
     * @since 25.07.2017
     * @version 1
     */
    private class ShowAllItems implements UserAction {

        @Override
        public int key() {
            return 1;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }

    /**
     * Class for edit the item in Tracker.
     * @author atrifonov
     * @since 25.07.2017
     * @version 1
     */
    private class EditItem implements UserAction {
        @Override
        public int key() {
            return 2;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Edit item");
        }
    }

    /**
     * Class for delete the item in Tracker.
     * @author atrifonov
     * @since 25.07.2017
     * @version 1
     */
    private static class DeleteItem implements UserAction{
        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Please, enter the item's Id:");
            Item item = new Item("", "", 123L);
            item.setId(id);
            tracker.delete(item);
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }
    }

    /**
     * Class for find the item by Id in Tracker.
     * @author atrifonov
     * @since 25.07.2017
     * @version 1
     */
    private static class FindItemById implements UserAction {
        @Override
        public int key() {
            return 4;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id");
        }
    }

}

/**
 * Class for find the item by name in Tracker.
 * @author atrifonov
 * @since 25.07.2017
 * @version 1
 */
class FindItemsByName implements UserAction {
    @Override
    public int key() {
        return 5;
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

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Find item by name");
    }
}

/**
 * Class for exit from Program.
 * @author atrifonov
 * @since 25.07.2017
 * @version 1
 */
class ExitProgram implements UserAction {
    @Override
    public int key() {
        return 6;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("Exit");
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Exit Program");
    }
}