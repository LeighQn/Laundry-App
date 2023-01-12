package com.example.laundry_app.USERS.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.Customer.CustomerProfileInterface;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;
import com.example.laundry_app.Global;
import com.example.laundry_app.USERS.Customer.MainFragments.CustomerStatusFragment;
import com.example.laundry_app.USERS.Customer.MainFragments.HomeFragment;
import com.example.laundry_app.USERS.Customer.MainFragments.ProfileFragment;
import com.example.laundry_app.USERS.Customer.MainFragments.StatusFragment;
import com.example.laundry_app.USERS.Customer.Screens.BookingActivity;
import com.example.laundry_app.USERS.Customer.Screens.CustomerProfileUpdate;
import com.example.laundry_app.USERS.Customer.Screens.NotificationActivity;
import com.example.laundry_app.MainActivity;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Staff.DashboardActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CustomerDashboard extends AppCompatActivity {

    // ============================== COMPONENTS ============================== //
    // ============================== COMPONENTS ============================== //

    BottomNavigationView bottomNavigationView;
    FloatingActionButton addLaundry;
    Button btnNotificationBell, btnLogout;
    Intent intent;

    // ============================== Objects ============================== //

    HomeFragment homeFragment = new HomeFragment();
    CustomerStatusFragment statusFragment = new CustomerStatusFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    CustomerProfileModel customerProfileModel;
    CustomerProfileInterface customerProfileInterface;
    //    Retrofit retrofit = Global.retrofitConnect();
//    String ip = Global.getIp();
    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);

    public String token, userId, phone, role, finalToken;

    // ============================================================================ //
    // ============================================================================ //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);

        // ============================== INITIALIZE ============================== //
        // ============================== INITIALIZE ============================== //

        addLaundry = findViewById(R.id.btn_add_customer_laundry);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        btnNotificationBell = findViewById(R.id.btn_notification_bell);
        btnLogout = findViewById(R.id.btn_logout);


        // ====================================== INITIALIZE RETROFIT ====================================== //
        // ====================================== INITIALIZE RETROFIT ====================================== //
        customerProfileInterface = retrofit.create(CustomerProfileInterface.class);


        // ============================== FUNCTION ============================== //
        // ============================== FUNCTION ============================== //

        // ============================== Notification Bell Button ============================== //

        btnNotificationBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendDataToNotif();
            }
        });

        // ============================== Logout Button ============================== //

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerDashboard.this, MainActivity.class);
                startActivity(intent);
                Global.setIp(ip);
                Toast.makeText(CustomerDashboard.this, "floating btn", Toast.LENGTH_SHORT).show();
            }
        });


        // ============================== Floating Button ============================== //

        addLaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.setIp(ip);
                sendDataToBook();
            }
        });


        // ============================== Home Display ============================== //
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
        intent = getIntent();
        token = intent.getStringExtra("token");


        // ============================== Button Visibility ============================== //
        notificationBellVisible(btnNotificationBell, btnLogout);


        // ============================== Bottom Navigation ============================== //

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.dasboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
                        notificationBellVisible(btnNotificationBell, btnLogout);
                        return true;

                    case R.id.status:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, statusFragment).commit();
                        notificationBellVisible(btnNotificationBell, btnLogout);
                        return true;

                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, profileFragment).commit();
                        notificationBellVisible(btnLogout, btnNotificationBell);
                        return true;
                }
                return false;
            }
        });


        getCustomerProfile();


    }

    // ============================== METHODS ============================== //
    // ============================== METHODS ============================== //

    public void notificationBellVisible(Button visible, Button invisible){
        visible.setVisibility(View.VISIBLE);
        invisible.setVisibility(View.INVISIBLE);
    }


    public void sendDataToBook(){
        intent = new Intent(this, BookingActivity.class );
        intent.putExtra("token", token);
        startActivity(intent);
    }


    public String getMyToken(){
        return token;
    }

    public void sendDataToNotif(){
        intent = new Intent(this, NotificationActivity.class );
        intent.putExtra("token", token);
        intent.putExtra("role", role);
        startActivity(intent);


    }



    private void getCustomerProfile(){

        finalToken = "Bearer " + token;
        Call<CustomerProfileModel> call = customerProfileInterface.getUserInfo(finalToken);
        call.enqueue(new Callback<CustomerProfileModel>() {
            @Override
            public void onResponse(Call<CustomerProfileModel> call, Response<CustomerProfileModel> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(CustomerDashboard.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                role = String.valueOf(response.body().getUser().getRole());
            }

            @Override
            public void onFailure(Call<CustomerProfileModel> call, Throwable t) {

            }
        });
    }



}