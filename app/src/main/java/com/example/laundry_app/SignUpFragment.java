package com.example.laundry_app;

import android.os.Bundle;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.laundry_app.Admin.AdminDashboard;

public class SignUpFragment extends Fragment {

    Button btn;

    AdminDashboard adminDashboard = new AdminDashboard();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_sign_up, null);

        btn = (Button) root.findViewById(R.id.btnSignUp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminDashboard.class);
                startActivity(intent);
            }
        });

        return root;


    }
}