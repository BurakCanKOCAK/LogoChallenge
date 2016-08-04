package com.bkocak.logochallenge.model;

/**
 * Created by bkocak on 25/07/2016.
 */
public class User {
    private String userName;
    private String mail;
    private String password;
    private String userId;
    private int level;

    public User() {
    }

    public User(String userName, String mail, String password, String userId, int level) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.level = level;
    }

    public String getUserName() {
        return userName;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }

    public int getLevel() {
        return level;
    }
}
