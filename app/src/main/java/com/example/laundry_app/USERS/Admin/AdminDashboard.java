package com.example.laundry_app.USERS.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.laundry_app.USERS.Admin.MainFragments.AdminHomeFragment;
import com.example.laundry_app.USERS.Admin.MainFragments.AdminProfileFragment;
import com.example.laundry_app.USERS.Admin.MainFragments.AdminRecordFragment;
import com.example.laundry_app.USERS.Admin.MainFragments.AdminSalesFragment;
import com.example.laundry_app.USERS.Admin.MainFragments.AdminStaffFragment;
import com.example.laundry_app.MainActivity;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Customer.Screens.BookingActivity;
import com.example.laundry_app.USERS.Staff.DashboardActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class AdminDashboard extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionBtn, floatingBtnAddLaundry, floatingBtnAddStaff, floatingBtnAddUnit ;
    Button btnLogout, btnNotif;
    Intent intent;

    AdminHomeFragment adminHomeFragment = new AdminHomeFragment();
    AdminRecordFragment adminRecordFragment = new AdminRecordFragment();
    AdminStaffFragment adminStaffFragment = new AdminStaffFragment();
    AdminProfileFragment adminProfileFragment = new AdminProfileFragment();
    AdminSalesFragment adminSalesFragment = new AdminSalesFragment();
    CustomerDashboard customerDashboard;

    Boolean isBol = true;
    String token, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        floatingActionBtn = findViewById(R.id.btn_add_main);
        floatingBtnAddLaundry = findViewById(R.id.btn_add_laundry);
        floatingBtnAddStaff = findViewById(R.id.btn_add_staff);
        floatingBtnAddUnit = findViewById(R.id.btn_add_unit);
        btnLogout = findViewById(R.id.btn_logout_admin);
        btnNotif = findViewById(R.id.btn_notification_bell);

        setFloatingActionButton(floatingActionBtn);
        adminNotificationBellVisible(btnNotif, btnLogout);


        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminHomeFragment).commit();        intent = getIntent();
        token = intent.getStringExtra("token");
        Toast.makeText(AdminDashboard.this, "Admin: " + token, Toast.LENGTH_SHORT).show();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){

                    case R.id.admin_dasboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminHomeFragment).commit();
                        receiver("Dashboard: ");
                        adminNotificationBellVisible(btnNotif, btnLogout);
                        return true;

                    case R.id.sales:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminSalesFragment).commit();
                        receiver("Sales: ");
                        adminNotificationBellVisible(btnNotif, btnLogout);
                        return true;

                    case R.id.record:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminRecordFragment).commit();
                        receiver("Record: ");
                        adminNotificationBellVisible(btnNotif, btnLogout);
                        return true;

                    case R.id.staffs:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminStaffFragment).commit();
                        receiver("Staff: ");
                        adminNotificationBellVisible(btnNotif, btnLogout);
                        return true;

                    case R.id.admin_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminProfileFragment).commit();
                        setLogoutIntent(btnLogout);
                        adminNotificationBellVisible(btnLogout, btnNotif);
                        receiver("Profile: ");
                        return true;

                }
                return false;
            }
        });

        // _________________________ FLOATING BUTTONS OPEN NEW ACTIVITY _________________________

        floatingBtnAddLaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, AdminBookActivity.class);
                startActivity(intent);
            }
        });

        floatingBtnAddUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AdminDashboard.this, UnitActivity.class);
                startActivity(intent1);
            }
        });

        floatingBtnAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(AdminDashboard.this, AddStaffActivity.class);
                startActivity(intent2);
            }
        });

    }

    public void setFloatingActionButton(FloatingActionButton floatingActionButton){
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBol){
                    floatingBtnAddLaundry.show();
                    floatingBtnAddStaff.show();
                    floatingBtnAddUnit.show();
                    isBol = false;
                }else{
                    floatingBtnAddLaundry.hide();
                    floatingBtnAddStaff.hide();
                    floatingBtnAddUnit.hide();
                    isBol = true;
                }
            }
        });
    }

    public void setLogoutIntent(Button btn){
        btn.setVisibility(View.VISIBLE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void receiver(String string){
        intent = getIntent();
        token = intent.getStringExtra("token");
        Toast.makeText(AdminDashboard.this, string + token, Toast.LENGTH_SHORT).show();
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

    public void adminNotificationBellVisible(Button visible, Button invisible){
        visible.setVisibility(View.VISIBLE);
        invisible.setVisibility(View.INVISIBLE);
    }
}