package com.project.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZoneResponse {

    @SerializedName("id")
    Long id;

    @SerializedName("villes")
    List<Ville> zones;

    String error;

    public ZoneResponse(List<Ville> zones, String error){
        this.zones =zones;
        this.error = error;
    }

    public List<Ville> getZones() {
        return zones;
    }

    public void setZones(List<Ville> zones) {
        this.zones = zones;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
