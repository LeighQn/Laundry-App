package com.example.laundry_app.API.INTERFACE;

import com.example.laundry_app.API.MODELCLASS.PriceRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface PriceInterface {
    @GET("laundries")
    Call<PriceRequest> getPrice(@Header("Authorization") String token);
}
