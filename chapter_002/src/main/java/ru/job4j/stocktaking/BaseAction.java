package ru.job4j.stocktaking;

/**
 * Created by Alexandr on 26.07.2017.
 */
public abstract class BaseAction implements UserAction {
    private String name;
    private int key;

    /**
     * Construct object BaseAction with name and key.
     * @param name Name of Action.
     * @param key Key of Action.
     */
    public BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }

    @Override
    public String info() {
        return String.format("%s. %s", key, name);
    }

    /**
     * Getter key.
     * @return key
     */
    public int getKey() {
        return key;
    }
}
