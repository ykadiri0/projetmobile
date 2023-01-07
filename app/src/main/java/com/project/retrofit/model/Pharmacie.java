package com.project.retrofit.model;

public class Pharmacie {
    private int id;
    private String name;
    private Double lat;
    private Double lon;
    private Zone zone;
    private String photo;

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Pharmacie(int id, String name, Double lat, Double lon, Zone zone, String photo) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.zone = zone;
        this.photo = photo;
    }


    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
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

    public void setName(String nom) {
        this.name = nom;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lon;
    }

    public void setLng(Double lng) {
        this.lon = lng;
    }


}
