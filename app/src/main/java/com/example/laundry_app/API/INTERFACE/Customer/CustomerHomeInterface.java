package com.example.laundry_app.API.INTERFACE.Customer;

import com.example.laundry_app.API.MODELCLASS.Customer.CustomerHomeModel;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface CustomerHomeInterface {

    @GET("customerDashboard")
    Call<CustomerHomeModel> getCustomerDashboard();

    @GET("auth/profile")
    Call<CustomerHomeModel> getCustomerInfoInHome(@Header("Authorization") String token);
}
