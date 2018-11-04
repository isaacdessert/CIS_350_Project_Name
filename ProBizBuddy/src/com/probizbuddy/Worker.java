package com.probizbuddy;

import java.util.Objects;

public class Worker {

    //instance variables
    String name;
    String password;
    int id;
    double wage;
    double timeWorked;

    //Constructor with param
    public Worker(String name, String password, int id, double wage, double timeWorked) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.wage = wage;
        this.timeWorked = timeWorked;
    }

    //Constructor
    public Worker() {
        this.name = "Worker";
        this.password = "password";
        this.id = 00000;
        this.wage = 0.0;
        this.timeWorked = 0.0;
    }

    /*
    Getters and setters below
     */
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

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getTimeWorked() {
        return timeWorked;
    }

    public void setTimeWorked(double timeWorked) {
        this.timeWorked = timeWorked;
    }

    //To string
    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", wage=" + wage +
                ", timeWorked=" + timeWorked +
                '}';
    }

    //equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return id == worker.id &&
                Double.compare(worker.wage, wage) == 0 &&
                Double.compare(worker.timeWorked, timeWorked) == 0 &&
                Objects.equals(name, worker.name) &&
                Objects.equals(password, worker.password);
    }

    //hash object, probably wont be needed.
    @Override
    public int hashCode() {
        return Objects.hash(name, password, id, wage, timeWorked);
    }
}
