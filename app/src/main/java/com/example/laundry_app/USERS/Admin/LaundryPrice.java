package com.example.laundry_app.USERS.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.laundry_app.Global;
import com.example.laundry_app.R;

import retrofit2.Retrofit;

public class LaundryPrice extends AppCompatActivity {

    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);

    TextView txtAvailableUnit, txtMaterialStatus;
    Button btnConfirmUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);

        txtAvailableUnit = findViewById(R.id.txt_weight_detailed_unit_display);
        txtMaterialStatus = findViewById(R.id.txt_total_status_detailed_unit_display);
        btnConfirmUnit = findViewById(R.id.btn_confirm_detergent_add_unit);


    }

    private void onClickBtnConfirmUnit(){
        btnConfirmUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Global.setIp(ip);
            }
        });
    }
}