package com.example.laundry_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.SignUpInterface;
import com.example.laundry_app.API.MODELCLASS.SignUp;
import com.example.laundry_app.USERS.Admin.AdminDashboard;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpFragment extends Fragment {


    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! CONVERT ADDRESS TO COORDINATES !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! //
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! CONVERT ADDRESS TO COORDINATES !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! //
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! CONVERT ADDRESS TO COORDINATES !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! //
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! CONVERT ADDRESS TO COORDINATES !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! //
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! CONVERT ADDRESS TO COORDINATES !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! //

    // ______________________________ COMPONENTS ______________________________ //
    // ______________________________ COMPONENTS ______________________________ //

    Button btnSignUpConfirm;
    EditText etxtFirstname, etxtMiddleName, etxtLastName, etxtUsername, etxtPhone, etxtPassword, etxtPurok;
    Spinner spinnerBarangay;
    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);




    // ______________________________ VARIABLES ______________________________ //
    // ______________________________ VARIABLES ______________________________ //

    String fullName, firstName, middleName, lastName, userName, phone, password, otp, barangay, purok, address ;




    // ______________________________ OBJECTS ______________________________ //
    // ______________________________ OBJECTS ______________________________ //

    AdminDashboard adminDashboard = new AdminDashboard();
    private SignUpInterface signUpInterface;
    private SignUp signUp;
    List<Address> addressList;



    ////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_sign_up, null);

        // ====================================== INITIALIZE ====================================== //
        // ====================================== INITIALIZE ====================================== //

        btnSignUpConfirm = (Button) root.findViewById(R.id.btn_sign_up);
        etxtFirstname = (EditText) root.findViewById(R.id.etxt_firstname);
        etxtLastName = (EditText) root.findViewById(R.id.etxt_lastname);
        etxtUsername = (EditText) root.findViewById(R.id.etxt_username);
        etxtPassword = (EditText) root.findViewById(R.id.etxt_password);
        etxtPhone = (EditText) root.findViewById(R.id.etxt_phone);
        spinnerBarangay = (Spinner) root.findViewById(R.id.spinner_barangay);
        etxtMiddleName = (EditText) root.findViewById(R.id.etxt_middlename);
        etxtPurok = (EditText) root.findViewById(R.id.etxt_purok);


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
        return root;
    }

    ////////////////////////////////////////////////////////////////////////////////////////// //////////////////////////////////////////////////////////////////////////////////////////

    // ====================================== METHODS ====================================== //
    // ====================================== METHODS ====================================== //



    // ______________________________ REDIRECT TO LOGIN ______________________________ //
    // ______________________________ REDIRECT TO LOGIN ______________________________ //


    private void redirectToLogin(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        if (etxtFirstname.getText().toString().isEmpty() || etxtLastName.getText().toString().isEmpty() ||
                etxtUsername.getText().toString().isEmpty() || etxtPassword.getText().toString().isEmpty() ||
                etxtPhone.getText().toString().isEmpty() || etxtPurok.getText().toString().isEmpty()) {
            alertDialog.setMessage("Kindly fillout the form.").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog alert = alertDialog.create();
            alert.setTitle("Dialog Header");
            alert.show();

        }else{
            alertDialog.setMessage("Signing Up").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    createSignUp();
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
        address = purok + " " + barangay + " Don Carlos, Bukidnon";

        Geocoder geocoder = new Geocoder(getActivity());


        try {
            addressList = geocoder.getFromLocationName(address, 1);

            if(addressList != null){
                String stringLat = String.valueOf(addressList.get(0).getLatitude());
                String stringLong = String.valueOf(addressList.get(0).getLongitude());

                Toast.makeText(getActivity(), "Lat: " + stringLat + " Long: " +stringLong, Toast.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(), address, Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // __________ calling object __________ //

        signUp = new SignUp(fullName, userName, phone, password, 3, address, String.valueOf(addressList.get(0).getLongitude()), String.valueOf(addressList.get(0).getLatitude()));   // add lat and long

        Call<SignUp> call = signUpInterface.createSignUp(signUp);
        call.enqueue(new Callback<SignUp>() {
            @Override
            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getActivity(), response.body() == null ? "Something went wrong with status code: " + response.code(): response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                // Code: 201 means created
                // Code: 200 mean successful
                SignUp signResponse = response.body();
                Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<SignUp> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // ______________________________ SPINNER EXECUTION ______________________________ //
    // ______________________________ SPINNER EXECUTION ______________________________ //

    private void spinnerExecution(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.barangay, android.R.layout.simple_spinner_item);
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

    // ______________________________ ADDRESS TO COORDINATES ______________________________ //
    // ______________________________ ADDRESS TO COORDINATES ______________________________ //



}