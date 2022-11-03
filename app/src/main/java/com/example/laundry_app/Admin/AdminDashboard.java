package com.example.laundry_app.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.laundry_app.Admin.MainFragments.AdminHomeFragment;
import com.example.laundry_app.Admin.MainFragments.AdminProfileFragment;
import com.example.laundry_app.Admin.MainFragments.AdminRecordFragment;
import com.example.laundry_app.Admin.MainFragments.AdminSalesFragment;
import com.example.laundry_app.Admin.MainFragments.AdminStaffFragment;
import com.example.laundry_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class AdminDashboard extends AppCompatActivity {

    String trial;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionBtn, floatingBtnAddLaundry, floatingBtnAddStaff, floatingBtnAddUnit ;

    AdminHomeFragment adminHomeFragment = new AdminHomeFragment();
    AdminRecordFragment adminRecordFragment = new AdminRecordFragment();
    AdminStaffFragment adminStaffFragment = new AdminStaffFragment();
    AdminProfileFragment adminProfileFragment = new AdminProfileFragment();
    AdminSalesFragment adminSalesFragment = new AdminSalesFragment();

    Boolean isBol = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        floatingActionBtn = findViewById(R.id.btn_add_main);
        floatingBtnAddLaundry = findViewById(R.id.btn_add_laundry);
        floatingBtnAddStaff = findViewById(R.id.btn_add_staff);
        floatingBtnAddUnit = findViewById(R.id.btn_add_unit);

        setFloatingActionButton(floatingActionBtn);

        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminHomeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){

                    case R.id.admin_dasboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminHomeFragment).commit();
                        return true;

                    case R.id.sales:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminSalesFragment).commit();
                        return true;

                    case R.id.record:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminRecordFragment).commit();
                        return true;

                    case R.id.staffs:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminStaffFragment).commit();
                        return true;

                    case R.id.admin_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminProfileFragment).commit();
                        return true;

                }
                return false;
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
}