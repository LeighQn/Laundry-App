package com.example.laundry_app.USERS.Staff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.laundry_app.Global;
import com.example.laundry_app.MainActivity;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Admin.AdminDashboard;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Customer.Screens.BookingActivity;
import com.example.laundry_app.USERS.Customer.Screens.NotificationActivity;
import com.example.laundry_app.USERS.Staff.MainFragments.StaffHomeFragment;
import com.example.laundry_app.USERS.Staff.MainFragments.StaffProfileFragment;
import com.example.laundry_app.USERS.Staff.MainFragments.StaffStatusFragment;
import com.example.laundry_app.USERS.Staff.MainFragments.StatusFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import retrofit2.Retrofit;

public class DashboardActivity extends AppCompatActivity {
    // ============================== COMPONENTS ============================== //
    // ============================== COMPONENTS ============================== //

    BottomNavigationView bottomNavigationView;
    Button btnNotificationBell, btnLogout;
    Intent intent;

    // ============================== Objects ============================== //

    StaffHomeFragment staffHomeFragment = new StaffHomeFragment();
    StatusFragment staffStatusFragment = new StatusFragment();
    StaffProfileFragment staffProfileFragment = new StaffProfileFragment();
    CustomerDashboard customerDashboard;

    String token, phone, role;

    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);


    // ============================================================================ //
    // ============================================================================ //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // ============================== INITIALIZE ============================== //
        // ============================== INITIALIZE ============================== //

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        btnNotificationBell = findViewById(R.id.btn_notification_bell);
        btnLogout = findViewById(R.id.btn_logout);

        // ============================== FUNCTION ============================== //
        // ============================== FUNCTION ============================== //

        // ============================== Home Display ============================== //
        getSupportFragmentManager().beginTransaction().replace(R.id.staff_frame_layout, staffHomeFragment).commit();
        intent = getIntent();
        token = intent.getStringExtra("token");
        Toast.makeText(DashboardActivity.this, "Admin: " + token, Toast.LENGTH_SHORT).show();

        // ============================== Bottom Navigation ============================== //


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.dasboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.staff_frame_layout, staffHomeFragment).commit();
                        noNotifBell(btnLogout, btnNotificationBell);
                        return true;

                    case R.id.status:
                        getSupportFragmentManager().beginTransaction().replace(R.id.staff_frame_layout, staffStatusFragment).commit();
                        noNotifBell(btnLogout, btnNotificationBell);
                        return true;

                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.staff_frame_layout, staffProfileFragment).commit();
                        staffNotificationBellVisible(btnLogout, btnNotificationBell);
                        return true;
                }
                return false;
            }
        });



        receiver("Dashboard Activity");


        btnNotificationBell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.setIp(ip);
                sendDataToNotifFromStaff();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(DashboardActivity.this, MainActivity.class);
                Global.setIp(ip);
                startActivity(intent);
            }
        });




    }

    public void sendDataToBook(){
        intent = new Intent(this, BookingActivity.class );
        intent.putExtra("token", token);
        intent.putExtra("phone", phone);
        startActivity(intent);
    }


    public String getMyToken(){
        return token;
    }

    public String getMyRole(){
        return role;
    }


    public void staffNotificationBellVisible(Button visible, Button invisible){
        visible.setVisibility(View.VISIBLE);
        invisible.setVisibility(View.INVISIBLE);
    }

    public void noNotifBell(Button invisible, Button invisible2){
        invisible.setVisibility(View.INVISIBLE);
        invisible2.setVisibility(View.INVISIBLE);
    }


    public void sendDataToNotifFromStaff(){
        intent = new Intent(this, NotificationActivity.class );
        intent.putExtra("token", token);
        intent.putExtra("role", "2");
        startActivity(intent);
        Toast.makeText(this, "Send to Notification", Toast.LENGTH_SHORT).show();


    }

    public void receiver(String string){
        intent = getIntent();
        token = intent.getStringExtra("token");
        Toast.makeText(DashboardActivity.this, "Booking Activity", Toast.LENGTH_SHORT).show();
    }

}