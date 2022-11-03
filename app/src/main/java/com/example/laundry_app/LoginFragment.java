package com.example.laundry_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.laundry_app.Customer.CustomerDashboard;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.laundry_app.R;



public class LoginFragment extends Fragment {

    Button btnLogin;

    CustomerDashboard customerDashboard = new CustomerDashboard();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_login, null);

       btnLogin = (Button) root.findViewById(R.id.btnLogin);

       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getActivity(), CustomerDashboard.class);
               startActivity(intent);
           }
       });

        return root;
    }
}