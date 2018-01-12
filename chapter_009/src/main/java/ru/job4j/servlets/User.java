package ru.job4j.servlets;

import java.time.LocalDateTime;

/**
 * Class for declare of user.
 * @author atrifonov.
 * @since 23.12.2017.
 * @version 1.
 */
public class User {
    /**
     * Id of user.
     */
    private int id;
    /**
     * Name of user.
     */
    private String name;
    /**
     * Login of user.
     */
    private String login;
    /**
     * Email of user.
     */
    private String email;
    /**
     * Role of user.
     */
    private String roleName;
    /**
     * Date of create of user.
     */
    private LocalDateTime createDate;
    /**
     * Password of user.
     */
    private String password;

    /**
     * Construct user without id.
     * @param name name of user.
     * @param login login of user.
     * @param email email of user.
     * @param roleName role of user.
     * @param createDate date of create user.
     * @param password password of user.
     */
    public User(String name, String login, String email, String roleName, LocalDateTime createDate, String password) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.roleName = roleName;
        this.createDate = createDate;
        this.password = password;
    }

    /**
     * Get name of user.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get login of user.
     * @return login.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Get email of user.
     * @return email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get createDate of user.
     * @return createDate.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * Get role of user.
     * @return role.
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * Get password of user.
     * @return password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get id.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set id.
     * @param id id.
     */
    public void setId(int id) {
        this.id = id;
    }
}
