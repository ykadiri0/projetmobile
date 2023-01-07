package com.project.retrofit.Dao;


import com.project.retrofit.model.Pharmacie;
import com.project.retrofit.model.PharmacieGard;
import com.project.retrofit.model.Ville;
import com.project.retrofit.model.Zone;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface JsonPlaceHolderApi {
    @GET("http://10.0.2.2:8081/getallphar")
    Call<ArrayList<Pharmacie>> allPositions();

    @GET("http://10.0.2.2:8081/getbyzoneandville")
    Call<ArrayList<Pharmacie>> allPositions(@Query("zone") String  zone);

    @GET("http://10.0.2.2:8081/getallville")
    Call<ArrayList<Ville>> allVille();

    @GET("http://10.0.2.2:8081/getallzonebyville")
    Call<ArrayList<Zone>> allZone(@Query("id") Integer id);;

    @GET("http://10.0.2.2:8081/getbyzoneandville")
    Call<ArrayList<Zone>> allphbyzone(@Query("zone") Zone zone);;

    @GET("http://10.0.2.2:8081/getallPG")
    Call<ArrayList<PharmacieGard>> allPG();

    @GET("http://10.0.2.2:8081/getty1")
    Call<ArrayList<PharmacieGard>> allPGbygard(@Query("id") Integer id);;

    @GET("http://10.0.2.2:8081/getallzonebyname")
    Call<ArrayList<Zone>> allZoneByName(@Query("name") String name);;

}
