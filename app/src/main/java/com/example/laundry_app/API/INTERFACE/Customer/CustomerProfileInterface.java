package com.example.laundry_app.API.INTERFACE.Customer;

import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;
import com.example.laundry_app.API.MODELCLASS.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface CustomerProfileInterface {

    @GET("auth/{userID}")
    Call<CustomerProfileModel> getCustomerInfo(@Path("userID") String id);

    @GET("auth/profile")
    Call<CustomerProfileModel> getUserInfo(@Header("Authorization") String token);

//
//    @GET("posts")
//    Call <List<CustomerProfileModel>> getAllInfo(@Header("Authorization") String token);

    @PATCH("auth/profile")
    Call<CustomerProfileModel> putCustomerInfo(@Path("id") int id, @Body CustomerProfileModel customerProfileModel);
}
