package com.project.retrofit.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VilleResponse {

    @SerializedName("id")
    Long id;

    @SerializedName("villes")
    List<Pharmacie> villes;

    String error;

    public VilleResponse(List<Pharmacie> villes, String error){
        this.villes =villes;
        this.error = error;
    }

    public List<Pharmacie> getVilles() {
        return villes;
    }

    public void setVilles(List<Pharmacie> villes) {
        this.villes = villes;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
