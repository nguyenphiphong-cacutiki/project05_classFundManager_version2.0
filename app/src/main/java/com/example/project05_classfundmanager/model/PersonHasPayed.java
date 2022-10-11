package com.example.project05_classfundmanager.model;

public class PersonHasPayed {
    private int id;
    private int type;
    private String name;
    private String date;
    private String note;

    public PersonHasPayed(int id, int type, String name, String date, String note) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.date = date;
        this.note = note;
    }

    public PersonHasPayed(int type, String name, String date, String note) {
        this.type = type;
        this.name = name;
        this.date = date;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
