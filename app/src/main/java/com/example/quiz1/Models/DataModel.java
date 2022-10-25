package com.example.quiz1.Models;

public class DataModel {
    private String first_name;
    private String last_name;
    private String image_view;
    private int id;

    public DataModel(int id, String first_name, String last_name, String image_view) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.image_view = image_view;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_view() {
        return image_view;
    }

    public void setImage_view(String image_view) {
        this.image_view = image_view;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}
