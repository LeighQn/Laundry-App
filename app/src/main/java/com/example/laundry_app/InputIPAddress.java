package com.example.laundry_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class InputIPAddress extends AppCompatActivity {

    TextView txtIp;
    Button btnIp;
    String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_ipaddress);

        txtIp = findViewById(R.id.txt_set_ip);
        btnIp = findViewById(R.id.btn_get_ip);

        txtIp.setText("192.168.254.104");

        ip = txtIp.getText().toString();
     //   Toast.makeText(this, ip, Toast.LENGTH_SHORT).show();



        btnIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputIPAddress.this, MainActivity.class);
                ip = ("http://" + txtIp.getText().toString() + ":8000/api/v1/");
          //      Toast.makeText(InputIPAddress.this, ip, Toast.LENGTH_SHORT).show();
                Global.setIp(ip);
                startActivity(intent);
            }
        });

//

    }

}