package ru.job4j.optimizationxml;

import javax.xml.bind.annotation.XmlType;

/**
 * Class for elements of Entries.
 */
@XmlType(propOrder = {"field"})
public class Entry {
    /**
     * Element field.
     */
    private int field;

    /**
     * Construct object Entry with field.
     * @param field value of field.
     */
    public Entry(int field) {
        this.field = field;
    }

    /**
     * Construct object Entry by default.
     */
    public Entry() {

    }

    /**
     * Getting value of field.
     * @return value of field.
     */
    public int getField() {
        return field;
    }

    /**
     * Setting value of field.
     * @param field new value of field.
     */
    public void setField(int field) {
        this.field = field;
    }

}
