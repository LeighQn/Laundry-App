package com.example.laundry_app.API.INTERFACE;

import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.API.MODELCLASS.BookingsRequest;
import com.example.laundry_app.API.MODELCLASS.Staff.BookingRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BookingInterface {
    @GET("bookings")
    Call<BookingsRequest> getBookings(@Header("Authorization") String token);

    @GET("bookings/detail/{id}")
    Call<BookingRequest> getBooking(@Header("Authorization") String token, @Path("id") String id);

    @POST("bookings/book")
    Call<List<BookingModel>> createBooking(@Header("Authorization") String token, @Path("id") String id, @Body BookingModel booking);
}
