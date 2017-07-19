package ru.job4j.stockTaking;

/**
 * Class Class describe object Item.
 * @author atrifonov
 * @since 19.07.2017
 * @version 1
 */
public class Item {
    private String name;
    private String description;
    private String Id;
    private long create;


    /**
     * Construct object Item without parameters.
     */
    public Item() {

    }

    /**
     * Construct object Item with name, description and create.
     * @param name The name of Item.
     * @param description The description of Item.
     * @param create The create of Item.
     */
    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
    }

    /**
     * Getter of name.
     * @return The name of Item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter of description.
     * @return The description of Item.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter of Id.
     * @return The Id of Item.
     */
    public String getId() {
        return this.Id;
    }

    /**
     * Setter of name.
     * @param name The name of Item.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter of description.
     * @param description The description of Item.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter of Id.
     * @param id The Id of Item.
     */
    public void setId(String id) {
        this.Id = id;
    }

    /**
     * Getter of create.
     * @return The create of Item.
     */
    public long getCreate() {
        return this.create;
    }

    /**
     * Setter of create.
     * @param create The create of Item.
     */
    public void setCreate(long create) {
        this.create = create;
    }

    /*
    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj.getClass() == this.getClass()) {
            Item objItem = (Item) obj;
            if (this.name.equals(objItem.name) && this.description.equals(objItem.description) && this.Id.equals(objItem.Id)) {
                equals = true;
            }
        }
        return equals;
    }
    */
}
