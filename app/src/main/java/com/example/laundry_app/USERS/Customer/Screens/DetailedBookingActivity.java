package com.example.laundry_app.USERS.Customer.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.laundry_app.R;

public class DetailedBookingActivity extends AppCompatActivity {

    Button btnBackToRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_booking);

        btnBackToRV = findViewById(R.id.btn_customer_booking_screen);

        btnBackToRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailedBookingActivity.this, CustomerBookingsActivity.class);
                startActivity(intent);
            }
        });
    }
}