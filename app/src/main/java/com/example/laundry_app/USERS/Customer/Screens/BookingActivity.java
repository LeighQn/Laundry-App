package com.example.laundry_app.USERS.Customer.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.BookingInterface;
import com.example.laundry_app.API.INTERFACE.Customer.CustomerProfileInterface;
import com.example.laundry_app.API.INTERFACE.PriceInterface;
import com.example.laundry_app.API.MODELCLASS.BookingLaundryModel;
import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.API.MODELCLASS.BookingRequest;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;
import com.example.laundry_app.API.MODELCLASS.Customer.LaundryBookModel;
import com.example.laundry_app.API.MODELCLASS.LaundryModel;
import com.example.laundry_app.API.MODELCLASS.PriceModel;
import com.example.laundry_app.API.MODELCLASS.PriceRequest;
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
    TextView etDatePicker, txtTotal, txtName, txtPhone, txtAddress;
    Calendar calendar;
    String convertedDate;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();
    CustomerProfileInterface customerProfileInterface;
    PriceInterface priceInterface;
    BookingInterface bookingInterface;
    PriceModel priceModel = new PriceModel();
    //    Retrofit retrofit = Global.retrofitConnect();
    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);

    // CALCULATION
    BookingModel bookingModel;
    User user;
    User customer;
    TextView txtRegular, txtWhite, txtMaong, txtComforter;
    Spinner spinnerRegClothes, spinnerMaong, spinnerWhite, spinnerComforter;


    Intent intent;

    String token, finalToken;
    String customerID;
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
        txtAddress = findViewById(R.id.etxt_address);

        //? CALCULATION
        bookingModel = new BookingModel();
        txtRegular = findViewById(R.id.txt_laundry_regular);
        txtWhite = findViewById(R.id.txt_laundry_white);
        txtMaong = findViewById(R.id.txt_laundry_maong);
        txtComforter = findViewById(R.id.txt_laundry_comforter);
        spinnerRegClothes = findViewById(R.id.spinner_reg_laundry);
        spinnerMaong = findViewById(R.id.spinner_maong_laundry);
        spinnerWhite = findViewById(R.id.spinner_white_laundry);
        spinnerComforter = findViewById(R.id.spinner_comforter_laundry);



        // ====================================== INITIALIZE RETROFIT ====================================== //
        // ====================================== INITIALIZE RETROFIT ====================================== //

        customerProfileInterface = retrofit.create(CustomerProfileInterface.class);
        priceInterface = retrofit.create(PriceInterface.class);
        bookingInterface = retrofit.create(BookingInterface.class);



        // ============================================== CALLING METHODS ================================================//
        receiver("User Id (BookingActivity): ");
        getDataForCreationOfBooking();


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
                if(bookingModel.getTotal() == 0){
                    Toast.makeText(BookingActivity.this, "Please select at least at least a kilo for the laundry", Toast.LENGTH_LONG).show();
                    return;
                }
                convertedDate = etDatePicker.getText().toString();
                Toast.makeText(BookingActivity.this, convertedDate, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BookingActivity.this);

                alertDialog.setMessage("Would you like to confirm the booking information? The subtotal is "
                                + String.valueOf(bookingModel.getSubTotal())
                                + ", and with the total " + String.valueOf(bookingModel.getTotal()))
                        .setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // ADD TO DATABASE
                        createBooking();
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
        if(intent.getStringExtra("customerID") == null){
            customerID = "none";
        }
        else{
            customerID = intent.getStringExtra("customerID");
        }

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


    private void getDataForCreationOfBooking(){
        finalToken = "Bearer " + token;
        Call<CustomerProfileModel> call;
        if(customerID != "none"){
            call = customerProfileInterface.getCustomerInfo(customerID);

        }
        else{
            call = customerProfileInterface.getUserInfo(finalToken);
        }
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
                populateSpinners();

            }

            @Override
            public void onFailure(Call<CustomerProfileModel> call, Throwable t) {
                Toast.makeText(BookingActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        Call<PriceRequest> priceRequest = priceInterface.getPrice(finalToken);
        priceRequest.enqueue(new Callback<PriceRequest>() {
            @Override
            public void onResponse(Call<PriceRequest> call, Response<PriceRequest> response) {
                if(response.code() != 200){
                    //TODO: GO BACK TO PREVIOUS ACTIVITY
                    return;
                }
                PriceRequest result = response.body();
                priceModel = result.getLaundry();
                populateMinimumTexts();
            }

            @Override
            public void onFailure(Call<PriceRequest> call, Throwable t) {
                //DO NOTHING
                //TODO: GO BACK TO PREVIOUS ACTIVITY
                return;
            }
        });

    }


    private void populateSpinners(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BookingActivity.this, R.array.laudry_unit_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegClothes.setAdapter(adapter);
        spinnerMaong.setAdapter(adapter);
        spinnerWhite.setAdapter(adapter);
        spinnerComforter.setAdapter(adapter);
        spinnerRegClothes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        spinnerWhite.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        spinnerMaong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
        spinnerComforter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    private void populateMinimumTexts(){
        txtComforter.setText(String.valueOf(priceModel.getComforterMin()));
        txtMaong.setText(String.valueOf(priceModel.getMaongMin()));
        txtWhite.setText(String.valueOf(priceModel.getWhiteMin()));
        txtRegular.setText(String.valueOf(priceModel.getRegularMin()));
    }

    private void calculate(){
        //TODO: calculate here
        //? FROM PRICE MODEL MINIMUM KG
        int kgRegMin = priceModel.getRegularMin();
        int kgWhiMin = priceModel.getWhiteMin();
        int kgMaoMin = priceModel.getMaongMin();
        int kgComMin = priceModel.getComforterMin();
        // MINIMUM KG FROM DATABASE
        int minmin = kgRegMin;
        if(minmin > kgWhiMin){
            minmin = kgWhiMin;
        }
        if(minmin > kgMaoMin){
            minmin = kgMaoMin;
        }
        if(minmin > kgComMin){
            minmin = kgComMin;
        }

        // SPINNER VALUES
        int kgReg = spinnerRegClothes.getSelectedItemPosition();
        int kgWhi = spinnerWhite.getSelectedItemPosition();
        int kgMao = spinnerMaong.getSelectedItemPosition();
        int kgCom = spinnerComforter.getSelectedItemPosition();

        // CHECK IF TOTAL KG IS 0
        int total = kgReg + kgWhi + kgMao + kgCom;


        // REAL CALCULATION
        int priWhi = priceModel.getWhitePrice();
        int priReg = priceModel.getRegularPrice();
        int priMao = priceModel.getMaongPrice();
        int priCom = priceModel.getComforterPrice();

        // Regular calculation
        int subReg = calculateSubTotal(kgReg, kgRegMin, priReg);
        int subWhi = calculateSubTotal(kgWhi, kgWhiMin, priWhi);
        int subMao = calculateSubTotal(kgMao, kgMaoMin, priMao);
        int subCom = calculateSubTotal(kgCom, kgComMin, priCom);
        int fee = user.getRole() == 1 ? 0 : 20;
        int subTotal =subReg + subWhi + subMao + subCom;
        int finalTotal = subTotal == 0 ? 0: subTotal+ fee;
        txtTotal.setText(String.valueOf(finalTotal));


        int type = user.getRole() == 1 ? 1 : 2;

        // set model values
        BookingLaundryModel laundryData = new BookingLaundryModel(kgReg, kgWhi, kgMao, kgCom);
        bookingModel.setLaundry(laundryData);
        bookingModel.setSubTotal(subTotal);
        bookingModel.setTotal(finalTotal);
        bookingModel.setType(type);
        bookingModel.setDate(etDatePicker.getText().toString());
    }


    private int calculateSubTotal (int kgSp, int kgDbMin, int dbPri){
        int total = 0;
        if(kgSp < kgDbMin){
            if(kgSp == 0) total =0;
            else total = dbPri * kgDbMin;
        }
        else total = kgSp * dbPri;
        return total;
    }


    private void createBooking(){
        finalToken = "Bearer " + token;
        if(customerID != "none"){
            bookingModel.setCustomer(user);
        }
        else{

        }
        Call<BookingRequest> request = bookingInterface.createBooking(finalToken, bookingModel);
        request.enqueue(new Callback<BookingRequest>() {
            @Override
            public void onResponse(Call<BookingRequest> call, Response<BookingRequest> response) {
                if(response.code() != 200){
                    //
                    Toast.makeText(BookingActivity.this, "Something went wrong with status code: "
                            + String.valueOf(response.code()), Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(BookingActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<BookingRequest> call, Throwable t) {

            }
        });
    }
}