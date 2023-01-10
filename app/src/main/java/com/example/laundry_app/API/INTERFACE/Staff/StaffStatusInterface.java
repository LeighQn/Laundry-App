package com.example.laundry_app.API.INTERFACE.Staff;

import com.example.laundry_app.API.MODELCLASS.Staff.StaffStatus;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StaffStatusInterface {

    // "posts" is the endpoint of the URL/ baseUrl, must change to the exact endpoint
    // <AdminSales> is the Model created earlier

    // Displays all
    @GET("posts")                                               // CHANGE TO THE EXACTS ENDPOINTS
    Call<List<StaffStatus>> getAllStaffStatus();

    // Displays by ID
    @GET("posts")
    Call<List<StaffStatus>> getStaffStatus(@Query("id") int id);

    // posts temporary
    // must put @Header("Authorization") String token in parameter
    @GET("posts")
    Call<List<StaffStatus>> getCustomerStatusInStaff(@Query("id") int id);


    // Sending data
    @POST("posts")
    Call<List<StaffStatus>> postStaffStatus(@Body StaffStatus staffStatus);

    // Completely change all data
    @PUT("posts/{id}")
    Call<List<StaffStatus>> putStaffStatus(@Path("id") int id, @Body StaffStatus staffStatus);

    // Change specific field
    @PATCH("posts")
    Call<List<StaffStatus>> patchStaffStatus(@Path("id") int id, @Body StaffStatus staffStatus);

    // Delete
    @DELETE("posts")
    Call<Void> deleteAdminSales(@Path("id") int id);

}
