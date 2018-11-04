package com.probizbuddy;

import java.util.Objects;

public class Manager {

    // instance variables
    String name;
    String password;
    int id;

    public Manager(String name, String password, int id) {
        this.name = name;
        this.password = password;
        this.id = id;

    }

    public Manager() {
        this.name = "Unknown";
        this.password = "password";
        this.id = 00000;

    }

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

    @Override
    public String toString() {
        return "Manager{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return id == manager.id &&
                Objects.equals(name, manager.name) &&
                Objects.equals(password, manager.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, id);
    }
}
