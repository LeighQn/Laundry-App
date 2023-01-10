package com.example.laundry_app.USERS.Admin.DetailedActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.laundry_app.USERS.Admin.AdminDashboard;
import com.example.laundry_app.R;

public class DetailedRecord extends AppCompatActivity {

    Button btnBack, btnAdd, btnMinus;
    EditText etxtWeight;
    String numberOfStock;
    int stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_record);



        btnBack = findViewById(R.id.btn_customer_delivered_screen_record);
        btnAdd = findViewById(R.id.btn_add_detergent);
        btnMinus = findViewById(R.id.btn_minus_detergent);
        etxtWeight = findViewById(R.id.etxt_add_detergent);


        numberOfStock = etxtWeight.getText().toString();
        stock = Integer.parseInt(etxtWeight.getText().toString());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stock = stock + 1;
                String convert = String.valueOf(stock);
                etxtWeight.setText(convert);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailedRecord.this, AdminDashboard.class);
                startActivity(intent);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stock == 0){
                    Toast.makeText(DetailedRecord.this, "The minimum count is 0", Toast.LENGTH_SHORT).show();
                }else {
                    stock = stock - 1;
                    String convert = String.valueOf(stock);
                    etxtWeight.setText(convert);
                }
            }
        });
    }

}