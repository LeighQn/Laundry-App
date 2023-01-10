package com.example.laundry_app.API.INTERFACE;

import com.example.laundry_app.API.MODELCLASS.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LoginInterface {

    @POST("login")
    Call<Login> handleLogin(@Body Login login);



//    @POST("login")
//    Call<Login> handleLogin(@Header ("Token-Header") String header, @Body Login login);

//    @POST("login")
//    Call<Login> handleLogin@Body Login login);
}
