package com.example.laundry_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.laundry_app.USERS.Admin.AdminDashboard;

public class AdminProfileUpdate extends AppCompatActivity {

    Button btnSave, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile_update);

        btnSave = findViewById(R.id.btn_save_admin_profile);
        btnBack = findViewById(R.id.btn_to_back_admin_profile_customer);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdminProfileUpdate.this);

                alertDialog.setMessage("Would you like save the changes?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // ADD TO DATABASE
                        AlertDialog.Builder yesAlert = new AlertDialog.Builder(AdminProfileUpdate.this);
                        yesAlert.setMessage("Successfully updated personal information.").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                                Intent intent = new Intent(AdminProfileUpdate.this, AdminDashboard.class);
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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminProfileUpdate.this, AdminDashboard.class);
                startActivity(intent);
            }
        });
    }
}