package com.example.laundry_app.USERS;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Staff.DashboardActivity;

public class OTPActivity extends AppCompatActivity {

    EditText etxtOtp;
    Button btnSubmit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);

        etxtOtp = findViewById(R.id.etxt_otp);
        btnSubmit = findViewById(R.id.btn_submit_otp);

        Intent intent = getIntent();
        String token = intent.getStringExtra("token");
        startActivity(intent);

        Toast.makeText(OTPActivity.this, "OTP: " + token, Toast.LENGTH_SHORT).show();
    }
}