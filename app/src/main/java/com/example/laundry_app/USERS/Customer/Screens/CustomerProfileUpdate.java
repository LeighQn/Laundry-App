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
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.R;

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

    String barangay, token, finalToken, name, phone, username, address;



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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.254.104:8000/api/v1/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        customerProfileInterface = retrofit.create(CustomerProfileInterface.class);



        // ==============================================================================================//

        // ====================================== CALLING METHODS ====================================== //
        // ====================================== CALLING METHODS ====================================== //

        spinnerExecution();
        receiver("Customer Profile Update: ");
        getProfileToUpdate();


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
                                backToCustomerDashboard();
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
                backToCustomerDashboard();
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

                etxtName.setText(name);
                etxtPhone.setText(phone);
            }

            @Override
            public void onFailure(Call<CustomerProfileModel> call, Throwable t) {

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
        Toast.makeText(CustomerProfileUpdate.this, string + token, Toast.LENGTH_SHORT).show();
    }

    //===================== RECEIVER

    public void sender(){
        intent = new Intent(this, BookLaundryTypeActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }


    public void backToCustomerDashboard(){
        intent = new Intent(this, CustomerDashboard.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }
}