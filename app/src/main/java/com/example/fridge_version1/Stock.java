package com.example.fridge_version1;

public class Stock {
    int id;
    int year;
    int month;
    int day;
    String name;
    String date;
    String dday;

    public int getId() {
        return id;
    }

    public int getYear() { return year; }

    public int getMonth() { return month; }

    public int getDay() { return day; }

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

    public void setYear(int year) { this.year = year; }

    public void setMonth(int month) { this.month = month; }

    public void setDay(int day) { this.day = day; }
}
