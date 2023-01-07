package com.project.retrofit.Controller;

import com.project.retrofit.Dao.Apiset;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    private static final String url="http://10.0.2.2:8081/";
    private static ApiController clientobject;
    private static Retrofit retrofit;

    public ApiController() {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static  synchronized ApiController getInstance(){
        if(clientobject == null){
            clientobject = new ApiController();
        }
        return clientobject;

    }
    public Apiset getapi() {
        return retrofit.create(Apiset.class);
    }
}
