package com.example.laundry_app.USERS.Customer.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.laundry_app.R;

public class DetailedDeliveredActivity extends AppCompatActivity {

    Button btnBackToDeliveredHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_delivered);

        btnBackToDeliveredHome = findViewById(R.id.btn_customer_delivered_screen);

        btnBackToDeliveredHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailedDeliveredActivity.this, CustomerDeliveredActivity.class);
                startActivity(intent);
            }
        });
    }
}