package ru.job4j.optimizationxml;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for root element file 1.xml.
 */
@XmlRootElement
public class Entries {
    /**
     * List of elements entry.
     */
    private List<Entry> entry;

    /**
     * Construct object Entries with list of entry.
     * @param entryList list of entry.
     */
    public Entries(List<Entry> entryList) {
        entry = new ArrayList<>();
        entry.addAll(entryList);
    }

    /**
     * Construct object Entries by default.
     */
    public Entries() {

    }

    /**
     * Getting list of entry.
     * @return list of entry.
     */
    public List<Entry> getEntry() {
        return entry;
    }

    /**
     * Setting new list of entry.
     * @param entry new list of entry.
     */
    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}
