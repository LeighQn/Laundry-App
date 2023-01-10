package com.example.laundry_app.USERS.Customer.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Customer.Screens.DataSetAndAdapter.CustomerDelivered;
import com.example.laundry_app.USERS.Customer.Screens.DataSetAndAdapter.CustomerDeliveredAdapter;
import com.example.laundry_app.R;

import java.util.ArrayList;

public class CustomerDeliveredActivity extends AppCompatActivity {

    Button btnBackToCustomerHome;

    private ArrayList<CustomerDelivered> cDeliveredArrayList;
    private String[] cDeliveredDates;
    private String[] cDeliveredPayables;
    private String[] cDeliveredStatus;
    private RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_delivered);

        btnBackToCustomerHome = findViewById(R.id.btn_customer_home);
        btnBackToCustomerHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerDeliveredActivity.this, CustomerDashboard.class);
                startActivity(intent);
            }
        });

        cDeliveredDataInitialize();

        recyclerView = findViewById(R.id.rv_display_all_delivered_customer);
        recyclerView.setLayoutManager(new LinearLayoutManager(CustomerDeliveredActivity.this));
        recyclerView.setHasFixedSize(true);
        CustomerDeliveredAdapter cDeliveredAdapter = new CustomerDeliveredAdapter(CustomerDeliveredActivity.this, cDeliveredArrayList);
        recyclerView.setAdapter(cDeliveredAdapter);
        cDeliveredAdapter.notifyDataSetChanged();
    }

    private void cDeliveredDataInitialize() {

        cDeliveredArrayList = new ArrayList<CustomerDelivered>();
        cDeliveredDates = new String[]{
                getString(R.string.date1),
                getString(R.string.date2),
                getString(R.string.date3),
        };

        cDeliveredPayables = new String[]{
                getString(R.string.total1),
                getString(R.string.total2),
                getString(R.string.total3),
        };

        cDeliveredStatus = new String[]{
                getString(R.string.status1),
                getString(R.string.status2),
                getString(R.string.status3),
        };
        for(int i=0; i < cDeliveredDates.length; i++){

            CustomerDelivered cDelivered = new CustomerDelivered(cDeliveredDates[i], cDeliveredPayables[i], cDeliveredStatus[i] );
            cDeliveredArrayList.add(cDelivered);
        }
    }
}