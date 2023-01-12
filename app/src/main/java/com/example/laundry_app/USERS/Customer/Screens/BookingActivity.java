package com.example.laundry_app.USERS.Customer.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.Customer.CustomerProfileInterface;
import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;
import com.example.laundry_app.API.MODELCLASS.User;
import com.example.laundry_app.Global;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Customer.MainFragments.ProfileFragment;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookingActivity extends AppCompatActivity {

    Button btnBackToCustomerDashboard, btnConfirmBooking, btnBookLaundry;
    TextView etDatePicker, txtTotal, txtName, txtPhone;
    Calendar calendar;
    String convertedDate;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();
    CustomerProfileInterface customerProfileInterface;
//    Retrofit retrofit = Global.retrofitConnect();
    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);

    // CALCULATION
    BookingModel bookingModel;
    User user;
    TextView txtRegular, txtWhite, txtMaong, txtComforter;


    Intent intent;

    String token, finalToken;

//    Date date = Calendar.getInstance().getTime();
//    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
//    String strDate = dateFormat.format(date);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        etDatePicker = findViewById(R.id.etxt_date_picker);
        btnBackToCustomerDashboard = findViewById(R.id.btn_customer_home);
        btnConfirmBooking = findViewById(R.id.btn_to_confirm_booking);
     //   btnBookLaundry = findViewById(R.id.btn_add_laundry_booking);
        txtTotal = findViewById(R.id.txt_total_booking_display);
        txtName = findViewById(R.id.txt_customer_name_display);
        txtPhone = findViewById(R.id.etxt_contact);

        //? CALCULATION
        bookingModel = new BookingModel();
        txtRegular = findViewById(R.id.txt_laundry_regular);
        txtWhite = findViewById(R.id.txt_laundry_white);
        txtMaong = findViewById(R.id.txt_laundry_maong);
        txtComforter = findViewById(R.id.txt_laundry_comforter);



        // ====================================== INITIALIZE RETROFIT ====================================== //
        // ====================================== INITIALIZE RETROFIT ====================================== //

        customerProfileInterface = retrofit.create(CustomerProfileInterface.class);



        // ============================================== CALLING METHODS ================================================//
        receiver("User Id (BookingActivity): ");
        getCustomerProfile();


        // --------------------- DATE PICKER --------------------/
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateCalendar();
            }

            private void updateCalendar() {
                String format = "MM/dd/yy";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);

                etDatePicker.setText(simpleDateFormat.format(calendar.getTime()));
            }
        };


        etDatePicker.setText(dtf.format(now));

        bookingModel.setDate(etDatePicker.getText().toString());
        etDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(BookingActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // --------------------- BACK TO CUSTOMER MAIN --------------------/

        btnBackToCustomerDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Global.setIp(ip);
                toCustomerDashboard();
            }
        });

        // --------------------- BUTTON TO CONFIRM BOOKING --------------------/

        btnConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertedDate = etDatePicker.getText().toString();
                Toast.makeText(BookingActivity.this, convertedDate, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BookingActivity.this);

                alertDialog.setMessage("Would you like to confirm the booking information?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // ADD TO DATABASE
                        AlertDialog.Builder yesAlert = new AlertDialog.Builder(BookingActivity.this);
                        yesAlert.setMessage("You have successfully booked your laundry").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                // ADD TO DATABASE
                                Global.setIp(ip);
                                toCustomerDashboard();
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

//        // --------------------- TO CHOOSE LAUNDRY TYPE --------------------/
//
//        btnBookLaundry.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                toLaundryType();
//            }
//        });
    }


    public void receiver(String string){
        intent = getIntent();
        token = intent.getStringExtra("token");
        Toast.makeText(BookingActivity.this, "Booking Activity", Toast.LENGTH_SHORT).show();
    }

    public void toLaundryType(){
        intent = new Intent(this, BookLaundryTypeActivity.class);
        intent.putExtra("token", token);
        intent.putExtra("booking", (Serializable) bookingModel);
        startActivity(intent);
    }

    public void toCustomerDashboard(){
        intent = new Intent(this, CustomerDashboard.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }


    // _________ GET _________ /


    private void getCustomerProfile(){

        finalToken = "Bearer " + token;
        Call<CustomerProfileModel> call = customerProfileInterface.getCustomerInfo(finalToken);
        call.enqueue(new Callback<CustomerProfileModel>() {
            @Override
            public void onResponse(Call<CustomerProfileModel> call, Response<CustomerProfileModel> response) {
                if(!response.isSuccessful() || response.code() != 200){
//                    txtName.setText("Code: " + String.valueOf(response.code()) );
                    Toast.makeText(BookingActivity.this, response.body() != null ? response.body().getMessage() : "Something went wrong", Toast.LENGTH_SHORT).show();
                    return;
                }
                user = response.body().getUser();
                bookingModel.setCustomer(user);
                String name = String.valueOf(response.body().getUser().getName());
                String phone = String.valueOf(response.body().getUser().getMobileNumber());
                //               String username = String.valueOf(response.body().getUser().getUsername());
                //              String address = String.valueOf(response.body().getUser().getAddress());

                txtName.setText(name);
                txtPhone.setText(phone);
//               txtuserName.setText(username);
//                txtAddress.setText(address);

            }

            @Override
            public void onFailure(Call<CustomerProfileModel> call, Throwable t) {
                Toast.makeText(BookingActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}