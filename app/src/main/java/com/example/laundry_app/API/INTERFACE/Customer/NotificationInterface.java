package com.example.laundry_app.API.INTERFACE.Customer;

import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;
import com.example.laundry_app.API.MODELCLASS.Customer.NotificationModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface NotificationInterface {

    @GET("profile")
    Call<NotificationModel> getNotification(@Header("Authorization") String token);
}
