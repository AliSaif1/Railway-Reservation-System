package com.example.databaseproject;

public class User {

    String name;
    String Lastname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public User(String name, String lastname) {
        this.name = name;
        this.Lastname = lastname;
    }
}
