package com.example.laundry_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    // ============================== COMPONENTS ============================== //
    // ============================== COMPONENTS ============================== //
    BottomNavigationView bottomNavigationView;
    TextView t1, t2;
    String username, password;
    MainActivity mainActivity;
    String ip;



    LoginFragment loginFragment = new LoginFragment();
    SignUpFragment signUpFragment = new SignUpFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ============================== INITIALIZE ============================== //
        // ============================== INITIALIZE ============================== //
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        t1 = findViewById(R.id.txt_one);
        t2 = findViewById(R.id.txt_TWO);




        // ============================== FUNCTIONS ============================== //
        // ============================== FUNCTIONS ============================== //

        // ============================== Home Visibility ============================== //
        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, loginFragment).commit();
        getIp();

        // ============================== Fragment View Changing ============================== //
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch(item.getItemId()){

                    case R.id.signIn:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, loginFragment).commit();
                        return true;

                    case R.id.signUp:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container_main, signUpFragment).commit();
                        return true;
                }

                return false;
            }
        });


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        LoginFragment loginFragment = new LoginFragment();
        fragmentTransaction.add(R.id.container_main, loginFragment);
        fragmentTransaction.commit();

    }


    // ============================== METHODS ============================== //
    // ============================== METHODS ============================== //

    // String receiver from other Activity
    public void receiver(String s1, String s2){
        username = s1;
        password = s2;
        t1.setText(username);
        t2.setText(password);

    }

    private void getIp(){
        Intent intent = getIntent();
        ip = intent.getStringExtra("ip");

    }

    public String sendIp(){
        return ip;
    }


}