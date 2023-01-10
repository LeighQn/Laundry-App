package com.example.laundry_app.USERS.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.USERS.Admin.Screens.AdminBookLaundryTypeActivity;

import com.example.laundry_app.R;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class AdminBookActivity extends AppCompatActivity {


    Button btnBackToAdminDashboard, btnAdminConfirmBooking, btnAdminBookLaundry;
    TextView etDatePicker, txtTotal;
    Calendar calendar;
    String convertedDate;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDateTime now = LocalDateTime.now();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_book);

        btnAdminBookLaundry = findViewById(R.id.btn_add_laundry_booking_admin);
        btnBackToAdminDashboard = findViewById(R.id.btn_home_admin);
        btnAdminConfirmBooking = findViewById(R.id.btn_to_confirm_booking_admin);
        etDatePicker = findViewById(R.id.etxt_date_picker_admin);
        txtTotal = findViewById(R.id.txt_total_sum_booked_admin);

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
        etDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AdminBookActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // --------------------- BACK TO CUSTOMER MAIN --------------------/

        btnBackToAdminDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminBookActivity.this, AdminDashboard.class);
                startActivity(intent);
            }
        });

        // --------------------- BUTTON TO CONFIRM BOOKING --------------------/

        btnAdminConfirmBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                convertedDate = etDatePicker.getText().toString();
                Toast.makeText(AdminBookActivity.this, convertedDate, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminBookActivity.this);

                alertDialog.setMessage("Would you like to confirm the booking information?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // ADD TO DATABASE
                        AlertDialog.Builder yesAlert = new AlertDialog.Builder(AdminBookActivity.this);
                        yesAlert.setMessage("You have successfully booked your laundry").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                Intent intent = new Intent(AdminBookActivity.this, AdminDashboard.class);
                                startActivity(intent);
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


        btnAdminBookLaundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminBookActivity.this, AdminBookLaundryTypeActivity.class);
                startActivity(intent);
            }
        });

//        txtName = findViewById(R.id.txt_customer_name);
//        datePicker = findViewById(R.id.etxt_date_picker_admin);
//        btnBackToAdminHome = findViewById(R.id.btn_admin_home_admin);
//        laundryType = findViewById(R.id.spinner_type_admin);
//        frameLayout = findViewById(R.id.container_cards_admin);
//        btnConfirmationDialogBox = findViewById(R.id.btn_to_confirm_booking_admin);
//        btnAddAdmin = findViewById(R.id.btn_add_laundry_booking_admin);
//
//
//        // --------------------- DATE PICKER --------------------/
//        Calendar calendar = Calendar.getInstance();
//        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
//
//                calendar.set(Calendar.YEAR, year);
//                calendar.set(Calendar.MONTH, month);
//                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//
//                updateCalendar();
//            }
//
//            private void updateCalendar(){
//                String format = "MM/dd/yy";
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
//
//                datePicker.setText(simpleDateFormat.format(calendar.getTime()));
//            }
//        };
//        datePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new DatePickerDialog(AdminBookActivity.this, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });
//
//        // --------------------- LAUNDRY TYPE SPINNER --------------------/
//        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, R.array.laudry_type_array, android.R.layout.simple_spinner_dropdown_item);
//        laundryType.setAdapter(typeAdapter);
//
//        // --------------------- BACK TO CUSTOMER MAIN --------------------/
//
//        btnBackToAdminHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(AdminBookActivity.this, AdminDashboard.class);
//                startActivity(intent);
//            }
//        });
//
//        // --------------------- BUTTON TO CONFIRM BOOKING --------------------/
//
//        btnConfirmationDialogBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                dateValue = datePicker.getText().toString();
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminBookActivity.this);
//
//                if (!itemsInAdmin.isEmpty()) {
//
//                        alertDialog.setMessage("Your total booking is: VARIABLE THAT STORES THE TOTAL HERE").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                // ADD TO DATABASE
//                                AlertDialog.Builder yesAlert = new AlertDialog.Builder(AdminBookActivity.this);
//                                yesAlert.setMessage("You have successfully booked your laundry").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialogInterface, int i) {
//                                        dialogInterface.cancel();
//                                        Intent intent = new Intent(AdminBookActivity.this, AdminDashboard.class);
//                                        startActivity(intent);
//                                    }
//                                });
//                                yesAlert.show();
//                            }
//                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                dialogInterface.cancel();
//                            }
//                        });
//                        AlertDialog alert = alertDialog.create();
//                        alert.setTitle("Dialog Header");
//                        alert.show();
//                    } else {
//                        alertDialog.setMessage("Please choose date and laundry type.").setPositiveButton("Okay", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialogInterface, int i) {
//                                // ADD TO DATABASE
//                            }
//                        });
//                        AlertDialog alert = alertDialog.create();
//                        alert.setTitle("Dialog Header");
//                        alert.show();
//                    }
//                }
//        });
//
//
//
//        // --------------------- ADD ITEM TO CARD VIEW/ RECYCLER VIEW --------------------/
//        RecyclerView recyclerView = findViewById(R.id.recycler_list_admin);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        LaundryTypeBookAdapter adapter = new LaundryTypeBookAdapter(itemsInAdmin);
//        recyclerView.setAdapter(adapter);
//
//        btnAddAdmin.setOnClickListener(view -> {
//            laundryType.getSelectedItemPosition();
//            itemsInAdmin.add(bookLaundryData[counter%3]);
//            counter++;
//            adapter.notifyItemInserted(itemsInAdmin.size()-1);
//        });
    }
}