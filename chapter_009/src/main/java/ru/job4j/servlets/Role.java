package ru.job4j.servlets;

/**
 * Class for role of user.
 * @author atrifonov.
 * @version 1.
 * @since 09.01.2018.
 */
public class Role {
    /**
     * Name of role.
     */
    private String roleName;
    /**
     * Capability of update.
     */
    private boolean canUpdateUser;
    /**
     * Capability of add.
     */
    private boolean canAddUser;
    /**
     * Capability of delete.
     */
    private boolean canDeleteUser;
    /**
     * Capability of change role.
     */
    private boolean canChangeRole;
    /**
     * Capability of add role.
     */
    private boolean canAddRole;
    /**
     * Id of role in database.
     */
    private int id;

    /**
     * Construct role.
     * @param roleName name of role.
     * @param canUpdateUser capability of update.
     * @param canAddUser capability of add.
     * @param canDeleteUser capability of delete.
     * @param canChangeRole capability of change role.
     * @param canAddRole capability of add role.
     */
    public Role(String roleName, boolean canUpdateUser, boolean canAddUser, boolean canDeleteUser, boolean canChangeRole, boolean canAddRole) {
        this.roleName = roleName;
        this.canUpdateUser = canUpdateUser;
        this.canAddUser = canAddUser;
        this.canDeleteUser = canDeleteUser;
        this.canChangeRole = canChangeRole;
        this.canAddRole = canAddRole;
    }

    /**
     * Get name of role.
     * @return name of role.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Get capability of update.
     * @return capability update.
     */
    public boolean isCanUpdateUser() {
        return canUpdateUser;
    }

    /**
     * Get capability of add.
     * @return capability add.
     */
    public boolean isCanAddUser() {
        return canAddUser;
    }

    /**
     * Get capability of delete.
     * @return capability delete.
     */
    public boolean isCanDeleteUser() {
        return canDeleteUser;
    }

    /**
     * Get capability of change role.
     * @return capability change role.
     */
    public boolean isCanChangeRole() {
        return canChangeRole;
    }

    /**
     * Get capability of add role.
     * @return capability add role.
     */
    public boolean isCanAddRole() {
        return canAddRole;
    }

    /**
     * Get id of role in database.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set id of role in database.
     * @param id role id.
     */
    public void setId(int id) {
        this.id = id;
    }
}
