package com.example.laundry_app.API.INTERFACE.Staff;

import com.example.laundry_app.API.MODELCLASS.Customer.CustomerHomeModel;
import com.example.laundry_app.API.MODELCLASS.Staff.StaffHomeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface StaffHomeInterface {

    @GET("profile")
    Call<StaffHomeModel> getStaffHomeModel(@Header("Authorization") String token);
}
