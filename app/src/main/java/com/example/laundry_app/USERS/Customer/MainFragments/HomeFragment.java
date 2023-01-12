package com.example.laundry_app.USERS.Customer.MainFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.Customer.CustomerHomeInterface;
import com.example.laundry_app.API.INTERFACE.Customer.CustomerProfileInterface;
import com.example.laundry_app.API.INTERFACE.SignUpInterface;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerHomeModel;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;
import com.example.laundry_app.Global;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Customer.Screens.CustomerBookingsActivity;
import com.example.laundry_app.USERS.Customer.Screens.CustomerDeliveredActivity;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Customer.Screens.CustomerProfileUpdate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {

    Button btnNumberOfBookings, btNumberOfDelivered;
    Intent intent;

    CustomerDashboard customerDashboard;
    CustomerHomeInterface customerHomeInterface;

    String token, finalToken;

    Retrofit retrofit = Global.retrofitConnect();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, null);


        // ====================================== INITIALIZE RETROFIT ====================================== //
        // ====================================== INITIALIZE RETROFIT ====================================== //
        btnNumberOfBookings = root.findViewById(R.id.btn_number_of_booking_home);
        btNumberOfDelivered = root.findViewById(R.id.btn_number_of_delivered_home);

        customerHomeInterface = retrofit.create(CustomerHomeInterface.class);



        // ============================================== CALLING METHODS ================================================//


        getDataFromActivityHome();
        getCustomerInfofromProfileToHome();

        return root;
    }

    // ============================================== METHODS ================================================//

    // _________ GET _________ /

    private void getCustomerInfofromProfileToHome(){
        finalToken = "Bearer " + token;
        Toast.makeText(getActivity(), finalToken, Toast.LENGTH_LONG).show();
        Call<CustomerHomeModel> call = customerHomeInterface.getCustomerInfoInHome(finalToken);
        call.enqueue(new Callback<CustomerHomeModel>() {
            @Override
            public void onResponse(Call<CustomerHomeModel> call, Response<CustomerHomeModel> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Customer Profile in Home Not Responding" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerHomeModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    // _________ GET _________ /


    // GETTING USER FROM ACTIVITY


    private void getDataFromActivityHome(){
        customerDashboard = (CustomerDashboard) getActivity();
        token = customerDashboard.getMyToken();
    }


}