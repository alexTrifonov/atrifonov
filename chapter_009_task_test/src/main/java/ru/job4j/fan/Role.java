package ru.job4j.fan;

/**
 * Class for entity role.
 */
public class Role {
    /**
     * Id of role.
     */
    private int id;
    /**
     * Name of role.
     */
    private String name;
    /**
     * Capability editing user.
     */
    private boolean editUser;
    /**
     * Capability editing role of user.
     */
    private boolean editRole;

    /**
     * Construct Role.
     * @param name name.
     * @param editUser capability edit user.
     * @param editRole capability edit role of user.
     */
    public Role(String name, boolean editUser, boolean editRole) {
        this.name = name;
        this.editUser = editUser;
        this.editRole = editRole;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isEditUser() {
        return editUser;
    }

    public boolean isEditRole() {
        return editRole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEditUser(boolean editUser) {
        this.editUser = editUser;
    }

    public void setEditRole(boolean editRole) {
        this.editRole = editRole;
    }
}
