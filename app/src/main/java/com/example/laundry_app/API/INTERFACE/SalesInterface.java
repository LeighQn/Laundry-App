package com.example.laundry_app.API.INTERFACE;

import com.example.laundry_app.API.MODELCLASS.Admin.AdminHomeModel;
import com.example.laundry_app.API.MODELCLASS.NewStatusModel;
import com.example.laundry_app.API.MODELCLASS.SalesModel;
import com.example.laundry_app.USERS.Admin.MainFragments.AdaptersAndDataClass.Sales;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface SalesInterface {

    @GET("profile")
    Call<List<SalesModel>> getSales(@Header("Authorization") String token);


}
