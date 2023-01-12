package com.example.laundry_app.API.INTERFACE;

import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.API.MODELCLASS.BookingsRequest;
import com.example.laundry_app.API.MODELCLASS.BookingRequest;

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
    Call<BookingRequest> getBooking(@Header("Authorization") String token);

    @POST("bookings/book/{id}")
    Call<BookingRequest> createBooking(@Header("Authorization") String token, @Path("id") String id, @Body BookingModel booking);
}
