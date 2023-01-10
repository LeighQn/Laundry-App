package com.example.laundry_app.USERS.Admin.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.USERS.Admin.AdminBookActivity;
import com.example.laundry_app.R;

public class AdminBookLaundryTypeActivity extends AppCompatActivity {

    Button btnBackToAdminBookActivity, btnOkay;
    Button btnAddRegClothes, btnMinusRegClothes, btnAddWhite, btnMinusWhite , btnAddMaong, btnMinusMaong, btnAddBedSheet,btnMinusBedSheet,
            btnAddComforter, btnMinusComforter;
    TextView etxtRegClothes, etxtWhite, etxtMaong, etxtBedSheet, etxtComforter, etxtDelivery;
    String totalSum, regClothes, white, maong, bedSheet, comforter;
    int total, convertRegClothes, convertDeliveryFee, convertWhite, convertMaong, convertBedSheet, convertComforter;
    int regClothesRate, whiteRate, maongRate, bedSheetRate, comforterRate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_book_laundry_type);


        // INITIALIZING LAYOUT
        //- initialize textview

        btnBackToAdminBookActivity = findViewById(R.id.btn_book_activity_home_admin);
        etxtRegClothes = findViewById(R.id.etxt_regular_clothes_admin);
        etxtWhite = findViewById(R.id.etxt_all_white_admin);
        etxtMaong = findViewById(R.id.etxt_maong_admin);
        etxtBedSheet = findViewById(R.id.etxt_bedsheet_admin);
        etxtComforter = findViewById(R.id.etxt_comforter_admin);
        etxtDelivery = findViewById(R.id.etxt_deliver_fee_admin);
        btnOkay = findViewById(R.id.btn_to_okay_booking_admin);

        // - add and minus button
        btnAddRegClothes = findViewById(R.id.btn_add_regClothes_admin);
        btnMinusRegClothes = findViewById(R.id.btn_minus_regClothes_admin);
        btnAddMaong = findViewById(R.id.btn_add_maong_admin);
        btnMinusMaong = findViewById(R.id.btn_minus_maong_admin);
        btnAddWhite = findViewById(R.id.btn_add_white_admin);
        btnMinusWhite = findViewById(R.id.btn_minus_white_admin);
        btnAddBedSheet = findViewById(R.id.btn_add_bedsheet_admin);
        btnMinusBedSheet = findViewById(R.id.btn_minus_bedsheet_admin);
        btnAddComforter = findViewById(R.id.btn_add_comforter_admin);
        btnMinusComforter = findViewById(R.id.btn_minus_comforter_admin);

        //__________ ADD UNIT FUNCTION __________

        addButton(btnAddRegClothes,etxtRegClothes, convertRegClothes);
        addButton(btnAddWhite, etxtWhite, convertWhite);
        addButton(btnAddMaong, etxtMaong, convertMaong);
        addButton(btnAddBedSheet, etxtBedSheet, convertBedSheet);
        addButton(btnAddComforter, etxtComforter, convertComforter);


        //__________ ADD UNIT FUNCTION __________

        minusButton(btnMinusRegClothes, etxtRegClothes, convertRegClothes);
        minusButton(btnMinusWhite, etxtWhite, convertWhite);
        minusButton(btnMinusMaong, etxtMaong, convertMaong);
        minusButton(btnMinusBedSheet, etxtBedSheet, convertBedSheet);
        minusButton(btnMinusComforter, etxtComforter, convertComforter);

        //_________ TOTAL SUM ____________

        btnOkay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //__________ CONVERT STRING TO INT __________
                convertRegClothes = Integer.parseInt(etxtRegClothes.getText().toString());
                convertDeliveryFee = Integer.parseInt(etxtDelivery.getText().toString());
                convertMaong = Integer.parseInt(etxtMaong.getText().toString());
                convertWhite = Integer.parseInt(etxtWhite.getText().toString());
                convertBedSheet = Integer.parseInt(etxtBedSheet.getText().toString());
                convertComforter = Integer.parseInt(etxtComforter.getText().toString());

                //_________ TOTAL RATE ____________

                convertDeliveryFee = 200;
                regClothesRate = 75; //change rate to db
                whiteRate = 50;
                maongRate = 50;
                bedSheetRate = 25;
                comforterRate = 50;

                if(convertRegClothes == 0 && convertWhite == 0 && convertMaong == 0 && convertBedSheet == 0
                        && convertComforter == 0){
                    toastMessage("Units must not be empty. - admin");
                }else{
                    convertRegClothes = convertRegClothes * regClothesRate;
                    convertWhite = convertWhite * whiteRate;
                    convertMaong = convertMaong* maongRate;
                    convertBedSheet= convertBedSheet * bedSheetRate;
                    convertComforter = convertComforter * comforterRate;

                    total = convertRegClothes + convertWhite + convertMaong + convertBedSheet + convertComforter + convertDeliveryFee;
                    totalSum = String.valueOf(total);
                    toastMessage(totalSum);
                }
            }
        });
        btnBackToAdminBookActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminBookLaundryTypeActivity.this, AdminBookActivity.class);
                startActivity(intent);
            }
        });
    }

    public void toastMessage(String string){
        Toast.makeText(AdminBookLaundryTypeActivity.this, string, Toast.LENGTH_SHORT).show();
    }

    public void addButton(Button add, TextView textView, Integer addNumber){
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer addNumber1 = addNumber;
                addNumber1 = Integer.parseInt(textView.getText().toString());

                addNumber1 = addNumber1 + 1;
                String convert = String.valueOf(addNumber1);
                textView.setText(convert);
            }
        });
    }

    public void minusButton(Button minus, TextView textView, Integer minusNumber){
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer minusNumber1 = minusNumber;
                minusNumber1 = Integer.parseInt(textView.getText().toString());

                if(minusNumber1 == 0){
                    Toast.makeText(AdminBookLaundryTypeActivity.this, "The minimum count is 0", Toast.LENGTH_SHORT).show();
                }else {
                    minusNumber1 = minusNumber1 - 1;
                    String convert = String.valueOf(minusNumber1);
                    textView.setText(convert);
                }
            }
        });
    }
}