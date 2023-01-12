package com.example.laundry_app.USERS.Customer.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.Customer.CustomerHomeInterface;
import com.example.laundry_app.API.INTERFACE.Customer.NotificationInterface;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerHomeModel;
import com.example.laundry_app.API.MODELCLASS.Customer.NotificationModel;
import com.example.laundry_app.Global;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.R;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NotificationActivity extends AppCompatActivity {

//    TextView txtNotification;

    Button btnBackToHome;
    Intent intent;
    NotificationInterface notificationInterface;
    NotificationModel notificationModel;

    String token, finalToken, role;

    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);



        btnBackToHome = findViewById(R.id.btn_back_to_home);
        notificationInterface = retrofit.create(NotificationInterface.class);


        receiverNotif("Received in Notification Page");
        getNotificationFromOtherActivity();


        btnBackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.setIp(ip);
                backToDashboardInNotif();
            }
        });

    }


    public void backToDashboardInNotif(){

        intent = new Intent(this, CustomerDashboard.class);
        intent.putExtra("token", token);
        Global.setIp(ip);
        startActivity(intent);
    }

//    private void getNotificationCustomer() {
//        finalToken = "Bearer " + token;
//        Call<NotificationModel> call = notificationInterface.getNotification(finalToken);
//        call.enqueue(new Callback<CustomerHomeModel>() {
//            @Override
//            public void onResponse(Call<CustomerHomeModel> call, Response<CustomerHomeModel> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getActivity(), "Customer Profile in Home Not Responding" + response.code(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CustomerHomeModel> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

    private void getNotificationFromOtherActivity(){
        finalToken = "Bearer " + token;
        Call<NotificationModel> call = notificationInterface.getNotification(finalToken);
        call.enqueue(new Callback<NotificationModel>() {
            @Override
            public void onResponse(Call<NotificationModel> call, Response<NotificationModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(NotificationActivity.this, "Notification Page is Not Responding", Toast.LENGTH_SHORT).show();
                    return;
                }

                NotificationModel notificationActivityResponse = response.body();

                role = String.valueOf(notificationActivityResponse.getUser().getRole());
                Toast.makeText(NotificationActivity.this, "Response: " + role, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<NotificationModel> call, Throwable t) {
                Toast.makeText(NotificationActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void receiverNotif(String string){
        intent = getIntent();
        token = intent.getStringExtra("token");
        String role = intent.getStringExtra("role");
        Toast.makeText(NotificationActivity.this, string, Toast.LENGTH_SHORT).show();
    }

    public void sendDatatoDashboardAct(){
        intent = new Intent(this, NotificationActivity.class );
        intent.putExtra("token", token);
        startActivity(intent);
        Toast.makeText(this, "Send to Notification", Toast.LENGTH_SHORT).show();



    }
}