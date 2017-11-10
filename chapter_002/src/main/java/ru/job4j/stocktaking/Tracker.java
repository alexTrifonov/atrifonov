package ru.job4j.stocktaking;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

/**
 * Class Class for store items.
 * @author atrifonov
 * @since 19.07.2017
 * @version 2
 */
public class Tracker {
    /**
     * Object for generate Id of Item.
     */
    private static final Random RN = new Random();

    /**
     * Const for getting command "make_table".
     */
    private static final String MAKE_TABLE = "make_table";
    /**
     * Const for getting command "add_new_item".
     */
    private static final String ADD_NEW_ITEM = "add_new_item";

    /**
     * Const for getting command "show_all_items".
     */
    private static final String SHOW_ALL_ITEMS = "show_all_items";

    /**
     * Const for getting command "edit_item".
     */
    private static final String EDIT_ITEM = "edit_item";

    /**
     * Const for getting command "delete_item".
     */
    private static final String DELETE_ITEM = "delete_item";

    /**
     * Const getting command "find_item_by_id".
     */
    private static final String FIND_ITEM_BY_ID = "find_item_by_id";

    /**
     * Const getting command "find_items_by_name".
     */
    private static final String FIND_ITEMS_BY_NAME = "find_items_by_name";
    /**
     * Store for command of PreparedStatement;
     */
    private Map<String, String> mapCommand;
    /**
     * Connection with database.
     */
    private Connection conn = null;
    /**
     * PreparedStatement for adding new Item.
     */
    private PreparedStatement prepStatAddItem = null;
    /**
     * PreparedStatement for getting all items.
     */
    private PreparedStatement prepStatmShowAll = null;
    /**
     * PreparedStatement for update item.
     */
    private PreparedStatement prepStatEditItem = null;
    /**
     * PreparedStatement for delete item.
     */
    private PreparedStatement prepStatDelItem = null;
    /**
     * PreparedStatement for getting item with assign id.
     */
    private PreparedStatement prepStatFindById = null;
    /**
     * PreparedStatement for getting all items with same name.
     */
    private PreparedStatement prepStatFindByName = null;
    /**
     * Construct object Tracker.
     */
    public Tracker() {

        fillMapCommand();
        connection();
    }

    /**
     * Create map with command for PreparedStatement and name of command.
     */
    private void fillMapCommand() {
        try {
            mapCommand = new HashMap<>();
            InputStream is = getClass().getResourceAsStream("connect_db.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] strings = line.split("-");
                this.mapCommand.put(strings[0], strings[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    /**
     * Make connection with database and create all PreparedStatement.
     */
    private void connection() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(mapCommand.get("url"), mapCommand.get("username"), mapCommand.get("password"));
            Statement statm = this.conn.createStatement();
            statm.execute(mapCommand.get(MAKE_TABLE));

            this.prepStatAddItem = this.conn.prepareStatement(mapCommand.get(ADD_NEW_ITEM));
            this.prepStatEditItem = this.conn.prepareStatement(mapCommand.get(EDIT_ITEM));
            this.prepStatmShowAll = this.conn.prepareStatement(mapCommand.get(SHOW_ALL_ITEMS));
            this.prepStatDelItem = this.conn.prepareStatement(mapCommand.get(DELETE_ITEM));
            this.prepStatFindById = this.conn.prepareStatement(mapCommand.get(FIND_ITEM_BY_ID));
            this.prepStatFindByName = this.conn.prepareStatement(mapCommand.get(FIND_ITEMS_BY_NAME));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Add item in items.
     * @param item added object.
     * @return added object or null if items is fill.
     */
    public Item add(Item item) {
        Item itemAdded = null;
        List<Item> allItems = findAll();

        if(item != null && allItems.size() < 100) {
            item.setId(generateId());
            try {
                this.prepStatAddItem.setString(1, item.getId());
                this.prepStatAddItem.setString(2, item.getName());
                this.prepStatAddItem.setString(3, item.getDescription());
                this.prepStatAddItem.setLong(4, item.getCreate());
                this.prepStatAddItem.executeUpdate();
                itemAdded = item;
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }


        return itemAdded;
    }

    /**
     * Update item of items with same Id.
     * @param item new value of item with same Id.
     */
    public void update(Item item) {
        if(item != null) {
            try {
                this.prepStatEditItem.setString(1, item.getName());
                this.prepStatEditItem.setString(2, item.getDescription());
                this.prepStatEditItem.setString(3, String.format("%s", item.getId()));
                this.prepStatEditItem.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }


    }

    /**
     * Delete item with same Id.
     * @param item deleted element.
     */
    public void delete(Item item) {
        if (item != null) {
            try {
                this.prepStatDelItem.setString(1, item.getId());
                this.prepStatDelItem.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }

    }

    /**
     * Find all not null elements.
     * @return array without null elements.
     */
    public List<Item> findAll() {
        return getItems(this.prepStatmShowAll, null);
    }

    /**
     * Find all elements with same name.
     * @param key name of element.
     * @return array of elements with same name.
     */
    public List<Item> findByName(String key) {
        return getItems(this.prepStatFindByName, key);
    }

    /**
     * Find element with same id.
     * @param id id of find element.
     * @return Item with same id or null if items not has element with same id.
     */
    protected Item findById(String id) {
        Item result = null;
        List<Item> itemList = getItems(this.prepStatFindById, id);
        if(itemList.size() != 0) {
            result = itemList.get(0);
        }
        return result;
    }

    /**
     * Create list of items with help PreparedStatement.
     * @param preparedStatement PreparedStatement for getting ResultSet.
     * @param paramFind parameter for PreparedStatement.
     * @return list of items.
     */
    private List<Item> getItems(PreparedStatement preparedStatement, String paramFind) {
        List<Item> items = new LinkedList<>();
        ResultSet resSet = null;
        try {
            if(paramFind != null) {
                preparedStatement.setString(1, paramFind);
            }
            resSet = preparedStatement.executeQuery();
            while (resSet.next()) {
                Item item = new Item(resSet.getString("name"), resSet.getString("description"), resSet.getLong("create_time"));
                item.setId(resSet.getString("id"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                if(resSet != null) {
                    resSet.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
            }
        }
        return items;
    }

    /**
     * generate id for item.
     * @return Id for assign item.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt(100));
    }

    /**
     * Close connection with database.
     */
    public void close() {
        try {
            if (this.conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}