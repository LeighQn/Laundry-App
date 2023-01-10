package com.example.laundry_app.USERS.Staff.MainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.laundry_app.Global;
import com.example.laundry_app.R;

import retrofit2.Retrofit;

public class StaffHomeFragment extends Fragment {

    // ============================== COMPONENTS ============================== //
    // ============================== COMPONENTS ============================== //

    TextView btnOutForDelivery, btnDelivered;
    Retrofit retrofit = Global.retrofitConnect();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_staff_home, null);

        // ============================== INITIALIZE ============================== //
        // ============================== INITIALIZE ============================== //

        btnOutForDelivery = root.findViewById(R.id.txt_display_number_of_delivery);
        btnDelivered = root.findViewById(R.id.txt_display_delivered);



        // ============================== FUNCTIONS ============================== //
        // ============================== FUNCTIONS ============================== //


        return root;
    }
}