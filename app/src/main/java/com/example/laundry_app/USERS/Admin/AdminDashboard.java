package com.example.laundry_app.USERS.Admin;

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
import com.example.laundry_app.USERS.Admin.MainFragments.AdminHomeFragment;
import com.example.laundry_app.USERS.Admin.MainFragments.AdminProfileFragment;
import com.example.laundry_app.USERS.Admin.MainFragments.AdminRecordFragment;
import com.example.laundry_app.USERS.Admin.MainFragments.AdminSalesFragment;
import com.example.laundry_app.USERS.Admin.MainFragments.AdminStaffFragment;
import com.example.laundry_app.MainActivity;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Admin.MainFragments.NewAdminSalesFragment;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Customer.Screens.BookingActivity;
import com.example.laundry_app.USERS.Customer.Screens.NotificationActivity;
import com.example.laundry_app.USERS.Staff.DashboardActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AdminDashboard extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionBtn, floatingBtnAddLaundry, floatingBtnAddStaff, floatingBtnAddUnit ;
    Button btnLogout, btnNotif;
    Intent intent;

    AdminHomeFragment adminHomeFragment = new AdminHomeFragment();
    AdminRecordFragment adminRecordFragment = new AdminRecordFragment();
    AdminStaffFragment adminStaffFragment = new AdminStaffFragment();
    AdminProfileFragment adminProfileFragment = new AdminProfileFragment();
    NewAdminSalesFragment adminSalesFragment = new NewAdminSalesFragment();
    CustomerDashboard customerDashboard;
    CustomerProfileInterface customerProfileInterface;
    Retrofit retrofit = Global.retrofitConnect();

    Boolean isBol = true;
    String token, phone, role, finalToken;


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
        btnLogout.setVisibility(View.GONE);

        // ====================================== INITIALIZE RETROFIT ====================================== //
        // ====================================== INITIALIZE RETROFIT ====================================== //
        customerProfileInterface = retrofit.create(CustomerProfileInterface.class);


        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminHomeFragment).commit();        intent = getIntent();
        token = intent.getStringExtra("token");
    //    Toast.makeText(AdminDashboard.this, "Admin: " + token, Toast.LENGTH_SHORT).show();

        requestTokenInAdminDashboard("Bearer " + token);


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){

                    case R.id.admin_dasboard:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminHomeFragment).commit();
                        receiver("Dashboard: ");
                        btnLogout.setVisibility(View.GONE);
                        return true;

                    case R.id.sales:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminSalesFragment).commit();
                        receiver("Sales: ");
                        btnLogout.setVisibility(View.GONE);
                        return true;

                    case R.id.record:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminRecordFragment).commit();
                        receiver("Record: ");
                        btnLogout.setVisibility(View.GONE);
                        return true;

                    case R.id.staffs:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminStaffFragment).commit();
                        receiver("Staff: ");
                        btnLogout.setVisibility(View.GONE);
                        return true;

                    case R.id.admin_profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.admin_frame_layout, adminProfileFragment).commit();
                        setLogoutIntent(btnLogout);
                        adminNotificationBellVisible(btnLogout);
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
                Intent intent2 = new Intent(AdminDashboard.this, AddEmployee.class);
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

    public void adminNotificationBellVisible(Button visible){
        visible.setVisibility(View.VISIBLE);
    }

    public void sendDataToAddEmployee(){
        intent = new Intent(this, AddEmployee.class );
        intent.putExtra("token", token);
        intent.putExtra("role", role);
        startActivity(intent);
        Toast.makeText(AdminDashboard.this, "Role in Customer Dashboard is: " + role, Toast.LENGTH_SHORT).show();


    }

    public void requestTokenInAdminDashboard(String finalToken){
//        finalToken = "Bearer " + token;
        Call<CustomerProfileModel> call = customerProfileInterface.getCustomerInfo(finalToken);
        call.enqueue(new Callback<CustomerProfileModel>() {
            @Override
            public void onResponse(Call<CustomerProfileModel> call, Response<CustomerProfileModel> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(AdminDashboard.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                role = String.valueOf(response.body().getUser().getRole());
                token = String.valueOf(response.body().getUser().getToken());
            }

            @Override
            public void onFailure(Call<CustomerProfileModel> call, Throwable t) {

            }
        });
    }
}