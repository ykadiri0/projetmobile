package com.project.retrofit.Dao;
import android.icu.text.Transliterator;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PositionAPI {
    @GET("/getallphar")
    Call<List<Transliterator.Position>> getPositions();
}
