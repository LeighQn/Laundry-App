package com.example.laundry_app.USERS.Staff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import com.example.laundry_app.API.INTERFACE.Customer.CustomerProfileInterface;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;
import com.example.laundry_app.API.MODELCLASS.User;
import com.example.laundry_app.Global;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Customer.Screens.BookingActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MapActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerStatus;
    Button btnNavigation;
    CustomerProfileInterface customerProfileInterface;
    DashboardActivity dashboardActivity;
    String token = Global.token;


    String latitude = "7.6809", longitude = "124.9865", status;


    Retrofit retrofit = Global.retrofitConnect();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        spinnerStatus = findViewById(R.id.spinner_status);
        btnNavigation = findViewById(R.id.btn_go_to_nav);


        // ====================================== INITIALIZE RETROFIT ====================================== //
        // ====================================== INITIALIZE RETROFIT ====================================== //
        customerProfileInterface = retrofit.create(CustomerProfileInterface.class);


        // ============================================== CALLING METHODS ================================================//

//
//        getDataFromActivityStaffProfile();
//        getCustomerProfile();
        spinnerExecution();


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

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void getCoordinates(){
        Call<CustomerProfileModel> call = customerProfileInterface.getCustomerInfo(token);
        call.enqueue(new Callback<CustomerProfileModel>() {
            @Override
            public void onResponse(Call<CustomerProfileModel> call, Response<CustomerProfileModel> response) {
                if(!response.isSuccessful()){
//                    Toast.makeText(MapActivity.this, "Code from MapACti")
                }
            }

            @Override
            public void onFailure(Call<CustomerProfileModel> call, Throwable t) {

            }
        });

    }

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
                Toast.makeText(adapterView.getContext(), status, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
