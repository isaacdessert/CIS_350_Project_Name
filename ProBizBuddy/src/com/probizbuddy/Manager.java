package com.probizbuddy;

import java.util.Objects;

public class Manager {

    // instance variables
    String name;
    String password;
    int id;

    //constructor with params
    public Manager(String name, String password, int id) {
        this.name = name;
        this.password = password;
        this.id = id;

    }

    //constructor
    public Manager() {
        this.name = "Unknown";
        this.password = "password";
        this.id = 00000;

    }

    //getters and setters below
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //toString method
    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    //equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return id == manager.id &&
                Objects.equals(name, manager.name) &&
                Objects.equals(password, manager.password);
    }

    //hash code probably wont be needed
    @Override
    public int hashCode() {
        return Objects.hash(name, password, id);
    }
}
