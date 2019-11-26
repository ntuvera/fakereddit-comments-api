package com.example.commentsapi.bean;

public class UserBean {
    private String username;

    public UserBean() { }

    public UserBean(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
