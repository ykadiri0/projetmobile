package com.project.retrofit.Dao;

import com.project.retrofit.model.Pharmacie;
import com.project.retrofit.model.Zone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apiset {
    @GET("getallphar")
    Call<List<Pharmacie>> getAllVilles();

    @GET("getallgard")
    Call<List<Zone>> getAllZones();
}
