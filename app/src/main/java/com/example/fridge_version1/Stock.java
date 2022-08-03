package com.example.fridge_version1;

public class Stock {
    int id;
    String name;
    String date;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() { return date; }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) { this.date = date; }
}
