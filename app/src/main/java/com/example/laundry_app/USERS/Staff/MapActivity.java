package com.example.laundry_app.USERS.Staff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.BookingInterface;
import com.example.laundry_app.API.INTERFACE.Customer.CustomerProfileInterface;
import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.API.MODELCLASS.BookingRequest;
import com.example.laundry_app.API.MODELCLASS.BookingsRequest;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;
import com.example.laundry_app.API.MODELCLASS.User;
import com.example.laundry_app.APIClient;
import com.example.laundry_app.Global;
import com.example.laundry_app.MainActivity;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Customer.Screens.BookingActivity;
import com.example.laundry_app.USERS.Customer.Screens.CustomerProfileUpdate;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerStatus;
    Button btnNavigation, btnConfirm;
    BookingInterface bookingInterface;
    DashboardActivity dashboardActivity;

    BookingModel bookingModel;
    Intent intent;

    TextView txtName, txtUsername, txtPhone, txtAddress;
    String latitude, longitude, status;
    String finalToken, token, role;
    String name, username, address, phone;

    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);

    String bookingID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        spinnerStatus = findViewById(R.id.spinner_status);
        btnNavigation = findViewById(R.id.btn_go_to_nav);
        btnConfirm = findViewById(R.id.btn_to_confirm_booking_admin);
        txtName = findViewById(R.id.txt_customer_name);
        txtUsername = findViewById(R.id.txt_customer_username);
        txtPhone = findViewById(R.id.txt_customer_phone);
        txtAddress = findViewById(R.id.txt_customer_address);


        // ====================================== INITIALIZE RETROFIT ====================================== //
        // ====================================== INITIALIZE RETROFIT ====================================== //
        bookingInterface = retrofit.create(BookingInterface.class);


        // ============================================== CALLING METHODS ================================================//

//
//        getDataFromActivityStaffProfile();
//        getCustomerProfile();
        spinnerExecution();
        receiver();
        getBookingsInMap();




        String destination = "&daddr=" + latitude + "," + longitude;
        String navigation = "google.navigation:q=" + latitude + "," + longitude + "&mode=d";


//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.laudry_status, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerStatus.setAdapter(adapter);
//        spinnerStatus.setOnItemSelectedListener(this);
//        webView.setWebViewClient(new WebViewClient());
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("https://maps.google.com/maps?" + "saddr=43.0054446, -87.9678884" + destination);
//        Toast.makeText(MapActivity.this, "Latidude is: " + latitude + "Longitude is:" + longitude, Toast.LENGTH_LONG).show();

        Toast.makeText(MapActivity.this, "Token from map:" + token, Toast.LENGTH_LONG).show();

        // GO TO NAVIGATION


        btnNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(navigation));
                intent.setPackage("com.google.android.apps.maps");

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBookingStatus();
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
   //     Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

//    private void getCoordinates(){
//        Call<BookingInterface> call = bookingInterface.getCustomerInfo(token);
//        call.enqueue(new Callback<CustomerProfileModel>() {
//            @Override
//            public void onResponse(Call<CustomerProfileModel> call, Response<CustomerProfileModel> response) {
//                if(!response.isSuccessful()){
////                    Toast.makeText(MapActivity.this, "Code from MapACti")
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CustomerProfileModel> call, Throwable t) {
//
//            }
//        });
//
//    }

    // ______________________________ SPINNER EXECUTION ______________________________ //
    // ______________________________ SPINNER EXECUTION ______________________________ //

    private void spinnerExecution(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MapActivity.this, R.array.laudry_status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                status = adapterView.getItemAtPosition(i).toString();
         //       Toast.makeText(adapterView.getContext(), status, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void receiver(){
        intent = getIntent();
        token = intent.getStringExtra("token");
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        role = intent.getStringExtra("role");
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");
        bookingID = intent.getStringExtra("bookingID");
        //Toast.makeText(CustomerProfileUpdate.this, string + token, Toast.LENGTH_SHORT).show();
    }

//    private void getBookings(){
//        finalToken = "Bearer " + token;
//        Call<BookingsRequest> call = bookingInterface.getBookings(finalToken);
//        call.enqueue(new Callback<BookingsRequest>() {
//            @Override
//            public void onResponse(Call<BookingsRequest> call, Response<BookingsRequest> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(getActivity(), "Admin Sale Fragment Code:" + response.code(), Toast.LENGTH_SHORT).show();
//                    bookingModelList.clear();
//                    return;
//                }
//
//                BookingsRequest  bookingsRequestResponse= response.body();
//                bookingModelList = bookingsRequestResponse.getBookings();
//
//                bookingAdapter.setBookingsListDatas(bookingModelList, listener);
//                recyclerView.setAdapter(bookingAdapter);
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<BookingsRequest> call, Throwable t) {
//                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });

    private void getBookingsInMap(){
        finalToken = "Bearer " + token;
        Call<BookingRequest> call = bookingInterface.getBooking(finalToken,bookingID);
        call.enqueue(new Callback<BookingRequest>() {
            @Override
            public void onResponse(Call<BookingRequest> call, Response<BookingRequest> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MapActivity.this, "Map Activity Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                BookingRequest bookingRequest= response.body();
                bookingModel = bookingRequest.getBooking();
                latitude = bookingRequest.getBooking().getCustomer().getLatitude();
                longitude = bookingRequest.getBooking().getCustomer().getLongitude();

                String name = bookingRequest.getBooking().getCustomer().getName();
                String username = bookingRequest.getBooking().getCustomer().getUsername();
                String phone = bookingRequest.getBooking().getCustomer().getMobileNumber();
                String address = bookingRequest.getBooking().getCustomer().getAddress();

                txtName.setText(name);
                txtUsername.setText(username);
                txtPhone.setText(phone);
                txtAddress.setText(address);
                spinnerStatus.setSelection(bookingRequest.getBooking().getStatus()-1);
            }

            @Override
            public void onFailure(Call<BookingRequest> call, Throwable t) {
                Toast.makeText(MapActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateBookingStatus(){
        finalToken = "Bearer " + token;
        bookingModel.setStatus(spinnerStatus.getSelectedItemPosition() + 1);
        Log.d("MAP_TEST_UPDATE", finalToken);
        Call<BookingRequest> request = bookingInterface.updateBookingStatus(finalToken, bookingID, bookingModel);
        request.enqueue(new Callback<BookingRequest>() {
            @Override
            public void onResponse(Call<BookingRequest> call, Response<BookingRequest> response) {
                if(!response.isSuccessful()){
                    Log.d("MAP_TEST_UPDATE", String.valueOf(response.code()));
                    Toast.makeText(MapActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MapActivity.this, "Status is now updated", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<BookingRequest> call, Throwable t) {

            }
        });
    }
}
