package com.example.laundry_app;

import static android.app.PendingIntent.getActivity;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;

import java.net.InterfaceAddress;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Global extends Application {

    public static String myIpAddress = "http://192.168.254.104:8000/api/v1/auth/";
    public static String homeIpAddress = "";
    public static String fakeApiIpAddress = "https://jsonplaceholder.typicode.com/";

    public static String baseAddress = "http://192.168.254.104:8000/api/v1/";

    public static Retrofit getClient(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit retrofitConnect(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(myIpAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit retrofitConnectFakeApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(fakeApiIpAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
