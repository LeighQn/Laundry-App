package com.example.laundry_app.USERS.Admin.DetailedActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.laundry_app.USERS.Admin.AdminDashboard;
import com.example.laundry_app.R;

public class DetailedSales extends AppCompatActivity {

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_sales);

        btnBack = findViewById(R.id.btn_customer_delivered_screen);


        Bundle b = getIntent().getExtras();
        String id = ""; // or other values
        if(b != null)
            id = b.getString("reservationStatusId");
        else
            id = "DetailedSales";
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailedSales.this, AdminDashboard.class);
                startActivity(intent);
            }
        });
    }
}