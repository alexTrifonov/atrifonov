package ru.job4j.fan;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for entity user.
 * @author atrifonov.
 * @version 1.
 * @since 28.01.2018.
 */
public class User {
    private int id;
    private String name;
    private String login;
    private Role role;
    private Address address;
    private String password;
    private List<MusicType> musicTypeList;
    private String musicListString;

    public User(String name, String login, Role role, Address address, String password) {
        this.name = name;
        this.login = login;
        this.role = role;
        this.address = address;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<MusicType> getMusicTypeList() {
        List<MusicType> list = new LinkedList<>();
        if (this.id > 0) {
            list = UserMusicStore.INSTANCE.getAllUserMusic(this);
        }
        return list;
    }

    public Role getRole() {

        return role;
    }

    public Address getAddress() {
        return address;
    }

    public String getMusicListString() {
        StringBuilder sb = new StringBuilder();
        LinkedList<MusicType> list = (LinkedList<MusicType>) getMusicTypeList();
        for(MusicType musicType : list) {
            sb.append(musicType.getType());
            if (musicType != list.getLast() ) {
                sb.append(",").append(" ");
            }
        }
        return sb.toString();
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMusicTypeList(List<MusicType> musicTypeList) {
        this.musicTypeList = musicTypeList;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
