package ru.job4j.taskGeneric;

/**
 * Class for model Role.
 * @author atrifonov.
 * @since 23.08.2017.
 * @version 1.
 */
public class Role extends Base {
    private int typeModel;

    public Role(int typeModel) {
        this.typeModel = typeModel;
    }

    public int getTypeModel() {
        return typeModel;
    }
}
