package com.example.laundry_app.USERS.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.SignUpInterface;
import com.example.laundry_app.API.MODELCLASS.SignUp;
import com.example.laundry_app.Global;
import com.example.laundry_app.MainActivity;
import com.example.laundry_app.R;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddEmployee extends AppCompatActivity {
    // ______________________________ COMPONENTS ______________________________ //
    // ______________________________ COMPONENTS ______________________________ //

    Button btnSignUpConfirm, btnBack;
    EditText etxtFirstname, etxtMiddleName, etxtLastName, etxtUsername, etxtPhone, etxtPassword, etxtPurok;
    TextView txtTitle;
    Spinner spinnerBarangay;
    Intent intent;
    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);




    // ______________________________ VARIABLES ______________________________ //
    // ______________________________ VARIABLES ______________________________ //

    String fullName, firstName, middleName, lastName, userName, phone, password, otp, role, purok, address, token ;




    // ______________________________ OBJECTS ______________________________ //
    // ______________________________ OBJECTS ______________________________ //

    AdminDashboard adminDashboard = new AdminDashboard();
    private SignUpInterface signUpInterface;
    private SignUp signUp;
    List<Address> addressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sign_up);


        // ====================================== INITIALIZE ====================================== //
        // ====================================== INITIALIZE ====================================== //

        btnSignUpConfirm = (Button) findViewById(R.id.btn_sign_up);
        etxtFirstname = (EditText) findViewById(R.id.etxt_firstname);
        etxtLastName = (EditText) findViewById(R.id.etxt_lastname);
        etxtUsername = (EditText) findViewById(R.id.etxt_username);
        etxtPassword = (EditText) findViewById(R.id.etxt_password);
        etxtPhone = (EditText) findViewById(R.id.etxt_phone);
        spinnerBarangay = (Spinner) findViewById(R.id.spinner_barangay);
        etxtMiddleName = (EditText) findViewById(R.id.etxt_middlename);
        etxtPurok = (EditText) findViewById(R.id.etxt_purok);
        txtTitle = (TextView) findViewById(R.id.txtSignUp);
        btnBack = (Button) findViewById(R.id.btn_home);

        txtTitle.setText("ADD EMPLOYEE");
        txtTitle.setGravity(Gravity.CENTER);
        etxtPurok.setText("Purok/ Zone, Barangay");
        btnBack.setVisibility(View.VISIBLE);




        // ====================================== INITIALIZE RETROFIT ====================================== //
        // ====================================== INITIALIZE RETROFIT ====================================== //
        signUpInterface = retrofit.create(SignUpInterface.class);


        // ====================================== FUNCTIONS ====================================== //
        // ====================================== FUNCTIONS ====================================== //


        // ______________________________ CALLING METHOD ______________________________ //
        // ______________________________ CALLING METHOD ______________________________ //

        spinnerExecution();

        // ______________________________ SIGN UP BUTTON ______________________________ //
        // ______________________________ SIGN UP BUTTON ______________________________ //

        btnSignUpConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.setIp(ip);
                redirectToLogin();
            }
        });



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(AddEmployee.this, AdminDashboard.class);
                Global.setIp(ip);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });

    }

    // ______________________________ REDIRECT TO LOGIN ______________________________ //
    // ______________________________ REDIRECT TO LOGIN ______________________________ //


    private void redirectToLogin(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddEmployee.this);
        if (etxtFirstname.getText().toString().isEmpty() || etxtLastName.getText().toString().isEmpty() ||
                etxtUsername.getText().toString().isEmpty() || etxtPassword.getText().toString().isEmpty() ||
                etxtPhone.getText().toString().isEmpty() || etxtPurok.getText().toString().isEmpty()) {
            alertDialog.setMessage("Kindly fillout the form.").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    createSignUp();
                }
            });
            AlertDialog alert = alertDialog.create();
            alert.setTitle("Dialog Header");
            alert.show();

        }else{
            alertDialog.setMessage("You have successfully Sign Up").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    createSignUp();
                    Intent intent = new Intent(AddEmployee.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            AlertDialog alert = alertDialog.create();
            alert.setTitle("Dialog Header");
            alert.show();
        }
    }


    // ______________________________ RETROFIT EXECUTION______________________________ //
    // ______________________________ RETROFIT EXECUTION______________________________ //

    private void createSignUp(){

        //  __________ initialize __________ //

        fullName = etxtFirstname.getText().toString()+ " " +
                etxtMiddleName.getText().toString() + " " +
                etxtLastName.getText().toString();

        userName = etxtUsername.getText().toString();
        phone = etxtPhone.getText().toString();
        password = etxtPassword.getText().toString();
        purok = etxtPurok.getText().toString();




        // __________ convert address to coordinates __________ //

        purok = etxtPurok.getText().toString();
        address = purok  + ", Don Carlos, Bukidnon";

        Geocoder geocoder = new Geocoder(AddEmployee.this);


        try {
            addressList = geocoder.getFromLocationName(address, 1);

            if(addressList != null){
                String stringLat = String.valueOf(addressList.get(0).getLatitude());
                String stringLong = String.valueOf(addressList.get(0).getLongitude());

                Toast.makeText(AddEmployee.this, "Lat: " + stringLat + " Long: " +stringLong, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), address, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // __________ calling object __________ //

        if(role.equals("Admin")){
            signUp = new SignUp(fullName, userName, phone, password, 1, address, String.valueOf(addressList.get(0).getLongitude()), String.valueOf(addressList.get(0).getLatitude()));   // add lat and long

        }else if (role.equals("Staff")){
            signUp = new SignUp(fullName, userName, phone, password, 2, address, String.valueOf(addressList.get(0).getLongitude()), String.valueOf(addressList.get(0).getLatitude()));   // add lat and long

        }else if (role.equals("Delivery Staff")){
            signUp = new SignUp(fullName, userName, phone, password, 2, address, String.valueOf(addressList.get(0).getLongitude()), String.valueOf(addressList.get(0).getLatitude()));   // add lat and long

        }else{
            Toast.makeText(AddEmployee.this, "Role is: " + role, Toast.LENGTH_SHORT).show();
        }

        createEmployee();

    }


    // ______________________________ SPINNER EXECUTION ______________________________ //
    // ______________________________ SPINNER EXECUTION ______________________________ //

    private void spinnerExecution(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(AddEmployee.this, R.array.employee_role, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBarangay.setAdapter(adapter);
        spinnerBarangay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                role = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(adapterView.getContext(), role, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // ______________________________ ADDRESS TO COORDINATES ______________________________ //
    // ______________________________ ADDRESS TO COORDINATES ______________________________ //

    private void createEmployee(){

        Call<SignUp> call = signUpInterface.createSignUp(signUp);
        call.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(AddEmployee.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                // Code: 201 means created
                // Code: 200 mean successful
                SignUp signResponse = response.body();
                Toast.makeText(AddEmployee.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                Toast.makeText(AddEmployee.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}