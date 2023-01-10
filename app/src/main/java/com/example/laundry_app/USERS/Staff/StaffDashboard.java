package com.example.laundry_app.USERS.Staff;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.laundry_app.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StaffDashboard extends AppCompatActivity {

    FloatingActionButton btnNavigation;
    String latitude = "15.0794"; // latitude value from db
    String longitude ="120.6200"; //longitude value from db
    Button btnGoToNewActivity;
    StaffMapFragment staffMapFragment = new StaffMapFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dashboard);

//        btnNavigation = findViewById(R.id.btn_go_to_map);
//        btnGoToNewActivity = findViewById(R.id.btn_open_new_activity);

        getSupportFragmentManager().beginTransaction().replace(R.id.staff_map_frame_layout, staffMapFragment).commit();

//        // ---------- BUTTON TO NAVIGATION ---------- //
//
//        btnNavigation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q={latitude}, {longitude}&mode=d"));
//                intent.setPackage("com.google.android.apps.maps");
//
//                if(intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);
//                }
//            }
//        });
//
//        // ---------- OPEN NEW ACTIVITY ---------- //
//
//        btnGoToNewActivity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(StaffDashboard.this, MapActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}