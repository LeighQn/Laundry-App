package com.example.laundry_app.USERS.Customer.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.Customer.CustomerProfileInterface;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;
import com.example.laundry_app.Global;
import com.example.laundry_app.USERS.Admin.AdminDashboard;
import com.example.laundry_app.USERS.Admin.MainFragments.AdminProfileFragment;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Customer.MainFragments.ProfileFragment;
import com.example.laundry_app.USERS.Staff.DashboardActivity;
import com.example.laundry_app.USERS.Staff.MainFragments.StaffProfileFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerProfileUpdate extends AppCompatActivity {


    // ====================================== GLOBAL COMPONENTS AND VARIABLES ====================================== //
    // ====================================== GLOBAL COMPONENTS AND VARIABLES ====================================== //

    Button btnSave, btnBack;
    CustomerProfileInterface customerProfileInterface;
    Spinner spinnerBarangay;
    EditText etxtName, etxtUserName, etxtPhone, etxtAddress;
    Intent intent;

    String barangay;
    String token;
    String finalToken;
    String name;
    String phone;
    String username;
    String address;
    static String role;

    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile_update);

        btnSave = findViewById(R.id.btn_save_customer_profile);
        btnBack = findViewById(R.id.btn_to_home_profile_saved_customer);
        spinnerBarangay = findViewById(R.id.spinner_barangay);
        etxtName = findViewById(R.id.txt_customer_name_update);
        etxtPhone = findViewById(R.id.txt_customer_phone_update);
        etxtUserName = findViewById(R.id.txt_customer_username_update);
        etxtAddress = findViewById(R.id.txt_customer_address_update);


        // ====================================== INITIALIZE RETROFIT ====================================== //
        // ====================================== INITIALIZE RETROFIT ====================================== //


        customerProfileInterface = retrofit.create(CustomerProfileInterface.class);


        // ==============================================================================================//

        // ====================================== CALLING METHODS ====================================== //
        // ====================================== CALLING METHODS ====================================== //

        spinnerExecution();
        receiver("Customer Profile Update: ");
        getProfileToUpdate();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.setIp(ip);
                intent = new Intent(CustomerProfileUpdate.this, DashboardActivity.class);
                Toast.makeText(CustomerProfileUpdate.this, role, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


        // ====================================== ONCLICK BUTTONS ====================================== //
        // ====================================== ONCLICK BUTTONS ====================================== //

        //===================== SAVE

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CustomerProfileUpdate.this);

                alertDialog.setMessage("Would you like save the changes?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // ADD TO DATABASE


                        AlertDialog.Builder yesAlert = new AlertDialog.Builder(CustomerProfileUpdate.this);
                        yesAlert.setMessage("Successfully updated personal information.").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                saveBtn();
                            }
                        });
                        yesAlert.show();
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alert = alertDialog.create();
                alert.setTitle("Dialog Header");
                alert.show();
            }
        });

        //===================== BACK

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToDashboard();
            }
        });

    }
    // ______________________________ SPINNER EXECUTION ______________________________ //
    // ______________________________ SPINNER EXECUTION ______________________________ //

    private void spinnerExecution(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(CustomerProfileUpdate.this, R.array.barangay, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBarangay.setAdapter(adapter);
        spinnerBarangay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                barangay = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(adapterView.getContext(), barangay, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // ==============================================================================================//

    // ======================================= METHODS =====================================//
    // ======================================= METHODS =====================================//

    //===================== GET

    private void getProfileToUpdate(){
        finalToken = "Bearer " + token;
        Call<CustomerProfileModel> call = customerProfileInterface.getCustomerInfo(finalToken);
        call.enqueue(new Callback<CustomerProfileModel>() {
            @Override
            public void onResponse(Call<CustomerProfileModel> call, Response<CustomerProfileModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(CustomerProfileUpdate.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                String name = String.valueOf(response.body().getUser().getName());
                String phone = String.valueOf(response.body().getUser().getName());
                String username = String.valueOf(response.body().getUser().getUsername());
                String address  = String.valueOf(response.body().getUser().getAddress());
                role = String.valueOf(response.body().getUser().getRole());

                etxtName.setText(name);
                etxtPhone.setText(phone);
                etxtUserName.setText(username);
                etxtAddress.setText(address);
            }

            @Override
            public void onFailure(Call<CustomerProfileModel> call, Throwable t) {
                Toast.makeText(CustomerProfileUpdate.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // ======================================= PASSING DATA =====================================//

    //===================== RECEIVER

    public void receiver(String string){
        intent = getIntent();
        token = intent.getStringExtra("token");
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        role = intent.getStringExtra("role");
        Toast.makeText(CustomerProfileUpdate.this, string + token, Toast.LENGTH_SHORT).show();
    }

    //===================== SENDER

    public void sender(){
        intent = new Intent(this, CustomerDashboard.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }


    public void backToDashboard(){

//        if(role.equals("1")){
//            openNewDashboard(AdminProfileFragment.class, "Back to Customer Dashboard");
//        }else if(role.equals("2")){
//            openNewDashboard(StaffProfileFragment.class, "Back to Staff Dashboard");
//        }else if(role.equals("3")){
//            openNewDashboard(ProfileFragment.class, "Back to Customer Dashboard");
//        }else{
//            return;

        if(role.equals("1")){
            Toast.makeText(CustomerProfileUpdate.this, "Admin Role", Toast.LENGTH_SHORT).show();
            openNewDashboard(AdminDashboard.class);
        }else if(role.equals("2")){
            Toast.makeText(CustomerProfileUpdate.this, "Staff Role", Toast.LENGTH_SHORT).show();
            openNewDashboard(DashboardActivity.class);
        }else if(role.equals("3")){
            Toast.makeText(CustomerProfileUpdate.this, "Customer Role", Toast.LENGTH_SHORT).show();
            openNewDashboard(CustomerDashboard.class);
        }else{
            Toast.makeText(this, "No Role", Toast.LENGTH_SHORT).show();
        }


    }


    public void saveBtn(){
        if(role.equals("1")){
            Toast.makeText(CustomerProfileUpdate.this, "Admin Save", Toast.LENGTH_SHORT).show();
            openNewDashboard(AdminDashboard.class);
        }else if(role.equals("2")){
            Toast.makeText(CustomerProfileUpdate.this, "Staff Save", Toast.LENGTH_SHORT).show();
            openNewDashboard(DashboardActivity.class);
        }else if(role.equals("3")){
            Toast.makeText(CustomerProfileUpdate.this, "Customer Save", Toast.LENGTH_SHORT).show();
            openNewDashboard(CustomerDashboard.class);
        }else{
            Toast.makeText(this, "Not Saved", Toast.LENGTH_SHORT).show();
        }

    }

    public void openNewDashboard(Class classes){
        intent = new Intent(this, classes);
        Global.setIp(ip);
        intent.putExtra("token", token);
        startActivity(intent);
    }
}