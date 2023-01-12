package com.example.laundry_app.USERS;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.AuthInterface;
import com.example.laundry_app.API.MODELCLASS.ActivationRequest;
import com.example.laundry_app.API.MODELCLASS.OtpModel;
import com.example.laundry_app.API.MODELCLASS.User;
import com.example.laundry_app.Global;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Admin.AdminDashboard;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Staff.DashboardActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OTPActivity extends AppCompatActivity {

    EditText etxtOtp;
    Button btnSubmit;
    String token;

    String ip = Global.getIp();
    Retrofit retrofit = Global.setIpRetrofit(ip);

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token);

        etxtOtp = findViewById(R.id.etxt_otp);
        btnSubmit = findViewById(R.id.btn_submit_otp);

        Intent intent = getIntent();
        token = intent.getStringExtra("token");




        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.setIp(ip);
                activateOtp();
            }
        });
    }

    private void activateOtp(){
        String finalToken = "Bearer " + token;
        String otp = etxtOtp.getText().toString();
        OtpModel otpModel = new OtpModel(otp);
        AuthInterface authInterface = retrofit.create(AuthInterface.class);
        Call<ActivationRequest> request = authInterface.activateWithOTP(finalToken, otpModel);
        request.enqueue(new Callback<ActivationRequest>() {
            @Override
            public void onResponse(Call<ActivationRequest> call, Response<ActivationRequest> response) {
                Log.d("OTP_TEST", String.valueOf(response.code()));
                if(!response.isSuccessful() || response.code() != 200){
                    Toast.makeText(OTPActivity.this, response.body() == null ? "OTP is invalid" : response.body().getMessage(), Toast.LENGTH_LONG);
                    Log.d("OTP_TEST", response.body().getMessage());
                    return;
                }
                ActivationRequest res = (ActivationRequest) response.body();
                User user = res.getUser();
                redirectedToAssignedRoleScreen(String.valueOf(user.getRole()));
            }

            @Override
            public void onFailure(Call<ActivationRequest> call, Throwable t) {
                Log.d("OTP_TEST", t.getMessage());
            }
        });
    }



    private void redirectedToAssignedRoleScreen(String role){

        if(role.equals("1")){

            passData(AdminDashboard.class, "Admin: ");

        }else if(role.equals("2")){

            passData(DashboardActivity.class, "Staff: ");

        }else if(role.equals("3")){

            passData(CustomerDashboard.class, "Customer: ");

        }else {
            Toast.makeText(OTPActivity.this, "Invalid Username or Password.", Toast.LENGTH_LONG).show();
        }

    }


    private void passData(Class classes, String string){
        intent = new Intent(OTPActivity.this, classes);
        intent.putExtra("token", token);
        startActivity(intent);
    }

}