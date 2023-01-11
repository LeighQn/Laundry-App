package com.example.laundry_app;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static Retrofit getClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.254.104:8000/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
