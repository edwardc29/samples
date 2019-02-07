package com.carrion.edward.cleanarchitectureapp.model;

public class MovieModel {

    private final int id;
    private String title;
    private int popularity;

    public MovieModel(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
}
