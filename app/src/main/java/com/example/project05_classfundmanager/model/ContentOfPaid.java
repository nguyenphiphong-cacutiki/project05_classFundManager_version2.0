package com.example.project05_classfundmanager.model;

public class ContentOfPaid {
    private int id;
    private String name;
    private double money;
    private String date;
    private String note;

    public ContentOfPaid(int id, String name, double money, String date, String note) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.date = date;
        this.note = note;
    }

    public ContentOfPaid(String name, double money, String date, String note) {
        this.name = name;
        this.money = money;
        this.date = date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
