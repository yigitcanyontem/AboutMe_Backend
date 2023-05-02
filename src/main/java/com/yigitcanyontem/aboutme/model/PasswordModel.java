package com.yigitcanyontem.aboutme.model;

public class PasswordModel {
    private String username;
    private String password;

    public PasswordModel() {
    }

    @Override
    public String toString() {
        return "PasswordModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
