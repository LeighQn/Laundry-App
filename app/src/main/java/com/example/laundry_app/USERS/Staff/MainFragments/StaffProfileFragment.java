package com.example.laundry_app.USERS.Staff.MainFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.API.INTERFACE.Customer.CustomerProfileInterface;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerProfileModel;
import com.example.laundry_app.Global;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Customer.Screens.CustomerProfileUpdate;
import com.example.laundry_app.USERS.Staff.DashboardActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StaffProfileFragment extends Fragment {

    Button btnUpdate, btnBack;
    CustomerProfileModel customerProfileModel;
    CustomerProfileInterface customerProfileInterface;
    TextView txtName, txtMn, txtLastName, txtUserName, txtPhone, txtAddress;
    String name, token, finalToken, phone, address;
    DashboardActivity dashboardActivity;
    Intent intent;

    Retrofit retrofit = Global.retrofitConnect();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, null);

        btnUpdate = root.findViewById(R.id.btn_update_customer_profile);
        btnBack = root.findViewById(R.id.btn_to_home_profile_customer);
        txtName = root.findViewById(R.id.txt_customer_name);
        txtPhone = root.findViewById(R.id.txt_customer_phone);
        txtUserName = root.findViewById(R.id.txt_customer_username);
        txtAddress = root.findViewById(R.id.txt_customer_address);

        // ====================================== INITIALIZE RETROFIT ====================================== //
        // ====================================== INITIALIZE RETROFIT ====================================== //
        customerProfileInterface = retrofit.create(CustomerProfileInterface.class);



        // ============================================== CALLING METHODS ================================================//


        getDataFromActivityStaffProfile();
        getCustomerProfile();


        // ==============================================================================================//



        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendDatatoUpdateProfileStaff();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toStaffDashboard();
            }
        });

        return root;
    }

    // _________ GET _________ /


    private void getCustomerProfile(){

        finalToken = "Bearer " + token;
        Call<CustomerProfileModel> call = customerProfileInterface.getCustomerInfo(finalToken);
        call.enqueue(new Callback<CustomerProfileModel>() {
            @Override
            public void onResponse(Call<CustomerProfileModel> call, Response<CustomerProfileModel> response) {
                if(!response.isSuccessful()){
                    txtName.setText("Code: " + String.valueOf(response.code()) );
//                    Toast.makeText(getActivity(), response.code() + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                CustomerProfileModel customerProfileModelResponse = response.body();

                String name = String.valueOf(customerProfileModelResponse.getUser().getName());
                String phone = String.valueOf(response.body().getUser().getMobileNumber());
                String username = String.valueOf(response.body().getUser().getUsername());
                String address = String.valueOf(response.body().getUser().getAddress());
                String token = String.valueOf(response.body().getUser().getToken());

                txtName.setText(name);
                txtPhone.setText(phone);
                txtUserName.setText(username);
                txtAddress.setText(address);

            }

            @Override
            public void onFailure(Call<CustomerProfileModel> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getDataFromActivityStaffProfile(){
        dashboardActivity = (DashboardActivity) getActivity();
        token = dashboardActivity.getMyToken();

    }

    private void toStaffDashboard(){
        intent = new Intent(getActivity(), DashboardActivity.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    private void sendDatatoUpdateProfileStaff(){
        intent = new Intent(getActivity(), CustomerProfileUpdate.class);
        intent.putExtra("token", token);
        startActivity(intent);
    }


}