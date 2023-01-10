package com.example.laundry_app.API.INTERFACE.Admin;

import com.example.laundry_app.API.MODELCLASS.Admin.AdminHomeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AdminHomeInterface {

    // add to parameter @Header("Authorization") String token
    @GET("posts")
    Call<List<AdminHomeModel>> getAllAdminHomeInfo();


    // change endpoint
    @GET("profile")
    Call<AdminHomeModel> getAdminHomeInfo(@Header("Authorization") String token);
}
