package com.example.laundry_app.API.INTERFACE.Customer;

import com.example.laundry_app.API.MODELCLASS.Customer.CustomerHomeModel;
import com.example.laundry_app.API.MODELCLASS.Customer.LaundryBookModel;
import com.example.laundry_app.API.MODELCLASS.LaundryPriceModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LaundryBookInterface {

    @POST("posts")
    Call<List<LaundryBookModel>> postLaundryBook(@Body LaundryBookModel laundryBookModel);

    @GET("posts")
    Call<List<LaundryBookModel>> getLaundryBook();

    @GET("profile")
    Call<LaundryBookModel> getCustomerInfoInLaundry(@Header("Authorization") String token);

    @GET("laundries")
    Call <List<LaundryPriceModel>> getLaundryPrice(@Header("Authorization") String token);
}
