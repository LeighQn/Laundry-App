package com.example.laundry_app.API.INTERFACE;

import com.example.laundry_app.API.MODELCLASS.Login;
import com.example.laundry_app.API.MODELCLASS.SignUp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SignUpInterface {

    // must change "posts" to the right endpoint
    @POST("register")
    Call<SignUp> createSignUp(@Body SignUp signUp);
}
