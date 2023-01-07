package com.project.retrofit.model;

public class Ville {
    private int id;
    private String name;

    public Ville(int id, String ville) {
        this.id = id;
        this.name = ville;
    }

    public Ville() {
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

    public void setName(String ville) {
        this.name = ville;
    }
}
