package com.example.laundry_app.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.laundry_app.R;

public class DetailedReservationStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_reservation_status);
        Bundle b = getIntent().getExtras();
        String id = ""; // or other values
        if(b != null)
            id = b.getString("reservationStatusId");
        else
            id = "wala ko id";
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();
    }
}