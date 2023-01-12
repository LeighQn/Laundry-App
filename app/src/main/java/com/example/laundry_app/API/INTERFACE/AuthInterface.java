package com.example.laundry_app.API.INTERFACE;

import com.example.laundry_app.API.MODELCLASS.ActivationRequest;
import com.example.laundry_app.API.MODELCLASS.Login;
import com.example.laundry_app.API.MODELCLASS.OtpModel;
import com.example.laundry_app.API.MODELCLASS.SignUp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AuthInterface {

    @POST("auth/login")
    Call<Login> handleLogin(@Body Login login);

    @POST("auth/register")
    Call<SignUp> createSignUp(@Body SignUp signUp);

    @PUT("auth/activate")
    Call<ActivationRequest> activateWithOTP(@Header("Authorization") String token, @Body OtpModel otpModel);



//    @POST("login")
//    Call<Login> handleLogin(@Header ("Token-Header") String header, @Body Login login);

//    @POST("login")
//    Call<Login> handleLogin@Body Login login);
}
