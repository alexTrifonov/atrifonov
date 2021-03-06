package ru.job4j.stocktaking;

import java.util.Date;

/**
 * Class Class describe object Item.
 * @author atrifonov
 * @since 19.07.2017
 * @version 1
 */
public class Item {
    /**
     * The name of item.
     */
    private String name;

    /**
     * The description of item.
     */
    private String description;

    /**
     * The id of item.
     */
    private String id;

    /**
     * The  time of create in milliseconds.
     */
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
     * Getter of id.
     * @return The id of Item.
     */
    public String getId() {
        return this.id;
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
     * Setter of id.
     * @param id The id of Item.
     */
    public void setId(String id) {
        this.id = id;
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


    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        if (obj != null && obj.getClass() == this.getClass()) {
            Item objItem = (Item) obj;
            if (this.name.equals(objItem.name)
                    && this.description.equals(objItem.description)
                    && Long.compare(this.create, objItem.create) == 0
                    && this.id.equals(objItem.id)) {
                equals = true;
            }
        }
        return equals;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"Name : \" ");
        sb.append(this.getName());
        sb.append(" \"Description : \" ");
        sb.append(this.getDescription());
        sb.append(" \"create : \" ");
        sb.append(new Date(this.getCreate()));
        sb.append(" \"Id : \" ");
        sb.append(this.getId());
        return sb.toString();
    }
}
