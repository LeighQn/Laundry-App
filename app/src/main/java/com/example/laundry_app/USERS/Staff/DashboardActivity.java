package com.example.laundry_app.USERS.Staff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Admin.AdminDashboard;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Customer.Screens.BookingActivity;
import com.example.laundry_app.USERS.Staff.MainFragments.StaffHomeFragment;
import com.example.laundry_app.USERS.Staff.MainFragments.StaffProfileFragment;
import com.example.laundry_app.USERS.Staff.MainFragments.StaffStatusFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends AppCompatActivity {
    // ============================== COMPONENTS ============================== //
    // ============================== COMPONENTS ============================== //

    BottomNavigationView bottomNavigationView;
    Button btnNotificationBell, btnLogout;
    Intent intent;

    // ============================== Objects ============================== //

    StaffHomeFragment staffHomeFragment = new StaffHomeFragment();
    StaffStatusFragment staffStatusFragment = new StaffStatusFragment();
    StaffProfileFragment staffProfileFragment = new StaffProfileFragment();
    CustomerDashboard customerDashboard;

    String token, phone;


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

        staffNotificationBellVisible(btnNotificationBell, btnLogout);

        // ============================== Bottom Navigation ============================== //


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.dasboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.staff_frame_layout, staffHomeFragment).commit();
                        staffNotificationBellVisible(btnNotificationBell, btnLogout);
                        return true;

                    case R.id.status:
                        getSupportFragmentManager().beginTransaction().replace(R.id.staff_frame_layout, staffStatusFragment).commit();
                        staffNotificationBellVisible(btnNotificationBell, btnLogout);
                        return true;

                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.staff_frame_layout, staffProfileFragment).commit();
                        staffNotificationBellVisible(btnLogout, btnNotificationBell);
                        return true;
                }
                return false;
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


    public void staffNotificationBellVisible(Button visible, Button invisible){
        visible.setVisibility(View.VISIBLE);
        invisible.setVisibility(View.INVISIBLE);
    }
}