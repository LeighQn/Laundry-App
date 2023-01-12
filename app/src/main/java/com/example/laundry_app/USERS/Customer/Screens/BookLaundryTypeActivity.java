package com.example.laundry_app.USERS.Customer.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.ADAPTERS.Customer.LaundryBookAdapter;
import com.example.laundry_app.API.INTERFACE.BookingInterface;
import com.example.laundry_app.API.INTERFACE.Customer.LaundryBookInterface;
import com.example.laundry_app.API.INTERFACE.PriceInterface;
import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.API.MODELCLASS.Customer.LaundryBookModel;
import com.example.laundry_app.API.MODELCLASS.LaundryPriceModel;
import com.example.laundry_app.API.MODELCLASS.PriceModel;
import com.example.laundry_app.API.MODELCLASS.PriceRequest;
import com.example.laundry_app.API.MODELCLASS.SignUp;
import com.example.laundry_app.APIClient;
import com.example.laundry_app.Global;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Customer.MainFragments.AdaptersAndDataClass.CustomerAdapter;
import com.example.laundry_app.USERS.Customer.MainFragments.AdaptersAndDataClass.Status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookLaundryTypeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    // API
    Retrofit retrofit = Global.retrofitConnect();
    BookingInterface bookingInterface;
    PriceInterface priceInterface;
    LaundryBookInterface laundryBookInterface;

    // DATA CLASS
    PriceModel priceModel;
    LaundryBookAdapter laundryBookAdapter;
    LaundryBookModel laundryBookModel;

    // Intent and Extras
    Intent intent;
    String token, finalToken;
    BookingModel bookingModel;

    // VARS
    String regClothes, allWhite, maong, comforter;
    // Get prices
    private ArrayList<LaundryPriceModel> laundryPriceModelArrayList;

    // COMPONENTS
    RecyclerView recyclerView;
    Spinner spinnerRegClothes, spinnerAllWhite, spinnerMaong, spinnerComforter;
    Button btnOkay, btnBack;


    //

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_laundry_type);


        // INITIALIZING LAYOUT
        //- initialize textview

        spinnerRegClothes = findViewById(R.id.spinner_reg_laundry);
        spinnerAllWhite = findViewById(R.id.spinner_white_laundry);
        spinnerMaong = findViewById(R.id.spinner_maong_laundry);
        spinnerComforter = findViewById(R.id.spinner_comforter_laundry);
        btnOkay = findViewById(R.id.btn_to_okay_booking);
        recyclerView = findViewById(R.id.rv_laundry_price_list);
        btnBack = findViewById(R.id.btn_book_activity_home);








        // ====================================== RETROFIT ====================================== //
        // ====================================== RETROFIT ====================================== //

        laundryBookInterface = retrofit.create(LaundryBookInterface.class);
        priceInterface = retrofit.create(PriceInterface.class);



        // ============================================== RECYCLERVIEW ================================================//

//        recyclerView.setLayoutManager(new LinearLayoutManager(BookLaundryTypeActivity.this));
//        recyclerView.addItemDecoration(new DividerItemDecoration(BookLaundryTypeActivity.this, DividerItemDecoration.VERTICAL));
//        laundryBookAdapter = new LaundryBookAdapter();

//        recyclerView. findViewById(R.id.rv_laundry_price_list);
//        recyclerView.setLayoutManager(new LinearLayoutManager(BookLaundryTypeActivity.this));
//        recyclerView.setHasFixedSize(true);
//        LaundryBookAdapter laundryBookAdapter = new LaundryBookAdapter(BookLaundryTypeActivity.this, laundryPriceModelArrayList);
//        laundryBookAdapter.notifyDataSetChanged();


        // ============================================== CALLING METHODS ================================================//
        spinnerExecution(spinnerRegClothes);
        spinnerExecution(spinnerAllWhite);
        spinnerExecution(spinnerMaong);
        spinnerExecution(spinnerComforter);

        receiver("Booking Laundry Type");
//        getLaundryPriceList();
//        getInfoFromLaundry();
        getPriceData();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToBookActivity();
            }
        });

    }


    public void receiver(String string){
        intent = getIntent();
        token = intent.getStringExtra("token");
        bookingModel = (BookingModel) intent.getSerializableExtra("booking");
        Toast.makeText(BookLaundryTypeActivity.this, bookingModel.getDate(), Toast.LENGTH_SHORT).show();
    }

    private void getPriceData(){
        finalToken = "Bearer " + token;
        Call<PriceRequest> call = priceInterface.getPrice(token);
        call.enqueue(new Callback<PriceRequest>() {
            @Override
            public void onResponse(Call<PriceRequest> call, Response<PriceRequest> response) {
                if(!response.isSuccessful() || response.code() != 200){
                    return;
                }
                priceModel = response.body().getLaundry();
            }

            @Override
            public void onFailure(Call<PriceRequest> call, Throwable t) {

            }
        });
    }

    private void getLaundryPriceList(){

        Call<List<LaundryBookModel>> call = laundryBookInterface.getLaundryBook();
        call.enqueue(new Callback<List<LaundryBookModel>>() {
            @Override
            public void onResponse(Call<List<LaundryBookModel>> call, Response<List<LaundryBookModel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(BookLaundryTypeActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

            }

            @Override
            public void onFailure(Call<List<LaundryBookModel>> call, Throwable t) {

            }
        });
    }

    // ______________________________ SPINNER EXECUTION ______________________________ //
    // ______________________________ SPINNER EXECUTION ______________________________ //

    private void spinnerExecution(Spinner spinner){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.laudry_unit_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        regClothes = adapterView.getItemAtPosition(i).toString();
        allWhite = adapterView.getItemAtPosition(i).toString();
        maong = adapterView.getItemAtPosition(i).toString();
        comforter = adapterView.getItemAtPosition(i).toString();
        //reg


        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(regClothes.equals("0 kg") && allWhite.equals("0 kg") && maong.equals("0 kg") && comforter.equals("0 kg")){

                    Toast.makeText(BookLaundryTypeActivity.this, "Please choose weight from the dropdown above.", Toast.LENGTH_SHORT).show();
                }else{
                    //Toast.makeText(BookLaundryTypeActivity.this, "Not Empty", Toast.LENGTH_SHORT).show();
                    laundryDialog();
                }
            }
        });

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void getInfoFromLaundry(){
        finalToken = "Bearer " + token;
        Call<LaundryBookModel> call = laundryBookInterface.getCustomerInfoInLaundry(finalToken);
        call.enqueue(new Callback<LaundryBookModel>() {
            @Override
            public void onResponse(Call<LaundryBookModel> call, Response<LaundryBookModel> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(BookLaundryTypeActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<LaundryBookModel> call, Throwable t) {
                Toast.makeText(BookLaundryTypeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void backToBookActivity(){
        intent = new Intent(this, BookingActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    public void laundryDialog(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(BookLaundryTypeActivity.this);

        alertDialog.setMessage("Would you like to confirm the booking information?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // ADD TO DATABASE
                AlertDialog.Builder yesAlert = new AlertDialog.Builder(BookLaundryTypeActivity.this);
                yesAlert.setMessage("Please be advised that there will be an additional 20 pesos for delivery.").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        // ADD TO DATABASE
                        backToBookActivity();
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

    private void createBookings(){


//        finalToken = "Bearer " + token;
//        Call<BookingModel> call = signUpInterface.createSignUp(signUp);
//        call.enqueue(new Callback<SignUp>() {
//            @Override
//            public void onResponse(Call<SignUp> call, Response<SignUp> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Code: 201 means created
//                // Code: 200 mean successful
//                SignUp signResponse = response.body();
//                Toast.makeText(getActivity(), "Code: " + response.code(), Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<SignUp> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}