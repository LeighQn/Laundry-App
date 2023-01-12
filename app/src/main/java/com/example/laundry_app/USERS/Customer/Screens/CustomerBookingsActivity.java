package com.example.laundry_app.USERS.Customer.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.BookingInterface;
import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.API.MODELCLASS.BookingsRequest;
import com.example.laundry_app.Global;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Customer.Screens.DataSetAndAdapter.CustomerBookings;
import com.example.laundry_app.USERS.Customer.Screens.DataSetAndAdapter.CustomerBookingsAdapter;
import com.example.laundry_app.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CustomerBookingsActivity extends AppCompatActivity {

    Retrofit retrofit;
    BookingInterface bookingInterface;

    Button btnBackToCustomerHome;

    private List<BookingModel> cBookingsArrayList;
    private String[] cBookingDates;
    private String[] cBookingPayables;
    private String[] cBookingStatus;
    private RecyclerView recyclerView;

    private String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_bookings);

        Retrofit retrofit = Global.retrofitConnect();
        bookingInterface = retrofit.create(BookingInterface.class);

        btnBackToCustomerHome = findViewById(R.id.btn_customer_home);

        btnBackToCustomerHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerBookingsActivity.this, CustomerDashboard.class);
                startActivity(intent);
            }

        });

        cBookingDataInitialize();

        recyclerView = findViewById(R.id.rv_display_all_booking_customer);
        recyclerView.setLayoutManager(new LinearLayoutManager(CustomerBookingsActivity.this));
        recyclerView.setHasFixedSize(true);
        CustomerBookingsAdapter cBookingAdapter = new CustomerBookingsAdapter(CustomerBookingsActivity.this, cBookingsArrayList);
        recyclerView.setAdapter(cBookingAdapter);
        cBookingAdapter.notifyDataSetChanged();
    }



    private void cBookingDataInitialize() {
        token = Global.token;
        Call<BookingsRequest> request = bookingInterface.getBookings(token);
        request.enqueue(new Callback<BookingsRequest>() {
            @Override
            public void onResponse(Call<BookingsRequest> call, Response<BookingsRequest> response) {
                BookingsRequest result = response.body();
                // data
                cBookingsArrayList = result.getBookings();
                String message = result.getMessage();
            }

            @Override
            public void onFailure(Call<BookingsRequest> call, Throwable t) {

            }
        });
//        cBookingsArrayList = new ArrayList<CustomerBookings>();
//        cBookingDates = new String[]{
//                getString(R.string.date1),
//                getString(R.string.date2),
//                getString(R.string.date3),
//        };
//
//        cBookingPayables = new String[]{
//                getString(R.string.total1),
//                getString(R.string.total2),
//                getString(R.string.total3),
//        };
//
//        cBookingStatus = new String[]{
//                getString(R.string.status1),
//                getString(R.string.status2),
//                getString(R.string.status3),
//        };
//        for(int i=0; i < cBookingDates.length; i++){
//
//            CustomerBookings cBookings = new CustomerBookings(cBookingDates[i], cBookingPayables[i], cBookingStatus[i] );
//            cBookingsArrayList.add(cBookings);
//        }
    }
}