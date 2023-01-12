package com.example.laundry_app.USERS.Admin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.Global;
import com.example.laundry_app.R;

import retrofit2.Retrofit;

public class UnitActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button btnBack, btnConfirm, btnAdd, btnMinus;
    String convertedDate = "Unit Activity", totalToString;
    TextView itemUnit, txtAddedUnit, txtMaterial;
    int convertedaddUnit, convertedTotal;
    Spinner spinner;
    private static final String[] paths = {"item 1", "item 2", "item 3"};

    String ip = Global.getIp();
    Retrofit retrofit = Global.setIpRetrofit(ip);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);

        btnBack = findViewById(R.id.btn_back_to_home_unit);
        btnConfirm = findViewById(R.id.btn_confirm_detergent_add_unit);
        itemUnit = findViewById(R.id.txt_weight_detailed_unit_display);
        spinner = findViewById(R.id.spinner);


        //__________ ADD and MINUS UNIT FUNCTION __________

        addButton(txtAddedUnit, convertedaddUnit);
        minusButton(txtAddedUnit, convertedaddUnit);


        //__________ BACK FUNCTION __________

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UnitActivity.this, AdminDashboard.class);
                startActivity(intent);
                Toast.makeText(UnitActivity.this, "Unit - admin", Toast.LENGTH_SHORT).show();
            }
        });

        //__________ CONFIRM FUNCTION __________

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(UnitActivity.this, convertedDate, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(UnitActivity.this);

                alertDialog.setMessage("Would you like to confirm the information?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // ADD TO DATABASE
                        AlertDialog.Builder yesAlert = new AlertDialog.Builder(UnitActivity.this);
                        yesAlert.setMessage("Success!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
//                                Intent intent = new Intent(UnitActivity.this, AdminDashboard.class);
//                                startActivity(intent);
                                Global.setIp(ip);
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

        //__________ SPINNER __________

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.laudry_material_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

//        txtMaterial.setPaintFlags(txtMaterial.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    // CUSTOMIZED METHODS

    public void addButton(TextView textView, Integer addNumber){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer addNumber1 = addNumber;
                addNumber1 = Integer.parseInt(textView.getText().toString());
                convertedTotal = addNumber1 + 1;

                String convert = String.valueOf(convertedTotal);
                textView.setText(convert);
                Toast.makeText(UnitActivity.this, convert, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void minusButton(TextView textView, Integer minusNumber){
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer minusNumber1 = minusNumber;
                minusNumber1 = Integer.parseInt(textView.getText().toString());

                if(minusNumber1 == 0){
                    Toast.makeText(UnitActivity.this, "The minimum count is 0", Toast.LENGTH_SHORT).show();
                }else {
                    minusNumber1 = minusNumber1 - 1;
                    String convert = String.valueOf(minusNumber1);
                    textView.setText(convert);
                    Toast.makeText(UnitActivity.this, convert, Toast.LENGTH_SHORT).show();
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
}