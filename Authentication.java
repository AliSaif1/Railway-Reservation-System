package com.example.databaseproject;

public class Authentication {
    private String userId;
    private String password;

    public Authentication(){

    }
    public Authentication(String userId, String password) {
        setUserId(userId);
        setPassword(password);
    }

    public String getUserId() {
        return userId;
    }

    private void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
