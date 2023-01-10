package com.example.laundry_app.API.INTERFACE.Customer;

import com.example.laundry_app.API.MODELCLASS.Customer.BookingActivityModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BookingActivityInterface {

    @POST("booking_activity")
    Call<BookingActivityModel> postBookingActivity(@Body BookingActivityModel bookingActivityModel);
}
