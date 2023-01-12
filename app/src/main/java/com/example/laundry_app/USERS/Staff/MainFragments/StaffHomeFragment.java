package com.example.laundry_app.USERS.Staff.MainFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.Customer.CustomerHomeInterface;
import com.example.laundry_app.API.INTERFACE.Staff.StaffHomeInterface;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerHomeModel;
import com.example.laundry_app.API.MODELCLASS.Staff.StaffHomeModel;
import com.example.laundry_app.Global;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Staff.DashboardActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StaffHomeFragment extends Fragment {

    // ============================== COMPONENTS ============================== //
    // ============================== COMPONENTS ============================== //

    TextView btnOutForDelivery, btnDelivered;
    String token, finalToken;
    StaffHomeInterface staffHomeInterface;
    DashboardActivity dashboardActivity;

    String ip = Global.getIp();
    Retrofit retrofit =Global.setIpRetrofit(ip);



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_staff_home, null);

        // ============================== INITIALIZE ============================== //
        // ============================== INITIALIZE ============================== //

        btnOutForDelivery = root.findViewById(R.id.txt_display_number_of_delivery);
        btnDelivered = root.findViewById(R.id.txt_display_delivered);

        staffHomeInterface = retrofit.create(StaffHomeInterface.class);




        // ============================================== CALLING METHODS ================================================//


        getDataFromActivityHomeStaff();
        getStaffInfoInHomeStaff();

        // ============================== FUNCTIONS ============================== //
        // ============================== FUNCTIONS ============================== //


        return root;
    }


    // _________ GET _________ /

    private void getStaffInfoInHomeStaff(){
        finalToken = "Bearer " + token;
        Call<StaffHomeModel> call = staffHomeInterface.getStaffHomeModel(finalToken);
        call.enqueue(new Callback<StaffHomeModel>() {
            @Override
            public void onResponse(Call<StaffHomeModel> call, Response<StaffHomeModel> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getActivity(), "Staff Profile in Home Not Repondinng"  + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<StaffHomeModel> call, Throwable t) {
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    // GETTING USER FROM ACTIVITY


    private void getDataFromActivityHomeStaff(){
        dashboardActivity = (DashboardActivity) getActivity();
        token = dashboardActivity.getMyToken();

    }
}