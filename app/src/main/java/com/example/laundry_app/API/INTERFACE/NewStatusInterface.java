package com.example.laundry_app.API.INTERFACE;

import com.example.laundry_app.API.MODELCLASS.NewStatusModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewStatusInterface {

    @GET("posts")
    Call<List<NewStatusModel>> getAllForDeliveryStatus();
}
