package com.example.laundry_app.API.INTERFACE.Customer;

import com.example.laundry_app.API.MODELCLASS.Customer.CustomerStatusModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CustomerStatusInterface {

    @GET("posts")
    Call <List<CustomerStatusModel>> getCustomerStatus();


}
