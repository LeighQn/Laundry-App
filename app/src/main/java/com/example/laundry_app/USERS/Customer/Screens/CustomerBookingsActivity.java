package com.example.laundry_app.USERS.Customer.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Customer.Screens.DataSetAndAdapter.CustomerBookings;
import com.example.laundry_app.USERS.Customer.Screens.DataSetAndAdapter.CustomerBookingsAdapter;
import com.example.laundry_app.R;

import java.util.ArrayList;

public class CustomerBookingsActivity extends AppCompatActivity {

    Button btnBackToCustomerHome;

    private ArrayList<CustomerBookings> cBookingsArrayList;
    private String[] cBookingDates;
    private String[] cBookingPayables;
    private String[] cBookingStatus;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_bookings);

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
        cBookingsArrayList = new ArrayList<CustomerBookings>();
        cBookingDates = new String[]{
                getString(R.string.date1),
                getString(R.string.date2),
                getString(R.string.date3),
        };

        cBookingPayables = new String[]{
                getString(R.string.total1),
                getString(R.string.total2),
                getString(R.string.total3),
        };

        cBookingStatus = new String[]{
                getString(R.string.status1),
                getString(R.string.status2),
                getString(R.string.status3),
        };
        for(int i=0; i < cBookingDates.length; i++){

            CustomerBookings cBookings = new CustomerBookings(cBookingDates[i], cBookingPayables[i], cBookingStatus[i] );
            cBookingsArrayList.add(cBookings);
        }
    }
}