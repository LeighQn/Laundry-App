package com.example.laundry_app.USERS.Customer.MainFragments;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.laundry_app.ADAPTERS.Customer.CustomerStatusAdapter;
import com.example.laundry_app.API.INTERFACE.BookingInterface;
import com.example.laundry_app.API.INTERFACE.Customer.CustomerProfileInterface;
import com.example.laundry_app.API.INTERFACE.Customer.CustomerStatusInterface;
import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.API.MODELCLASS.BookingRequest;
import com.example.laundry_app.API.MODELCLASS.BookingsRequest;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerStatusModel;
import com.example.laundry_app.Global;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Customer.MainFragments.AdaptersAndDataClass.CustomerAdapter;
import com.example.laundry_app.USERS.Customer.MainFragments.AdaptersAndDataClass.Status;
import com.example.laundry_app.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StatusFragment extends Fragment {


    private ArrayList<BookingModel> statusArrayList;
    private String[] statusDate;
    private String[] statusPayable;
    private String[] statusStatus;
    private RecyclerView recyclerView;

    Intent intent;

    CustomerStatusAdapter customerStatusAdapter;

    Retrofit retrofit;
    BookingInterface bookingInterface;
    String token;


    CustomerDashboard customerDashboard;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // ====================================== INITIALIZE RETROFIT ====================================== //
        // ====================================== INITIALIZE RETROFIT ====================================== //

        String ip = Global.getIp();
        retrofit =Global.setIpRetrofit(ip);
        bookingInterface = retrofit.create(BookingInterface.class);


        // ==============================================================================================//
        getDataFromActivity();
        getAllBookings();

        //dataInitialized();

        recyclerView = view.findViewById(R.id.rv_status_customer);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        CustomerAdapter customerAdapter = new CustomerAdapter(getContext(), statusArrayList);
        recyclerView.setAdapter(customerAdapter);
        customerAdapter.notifyDataSetChanged();
    }



    private void getDataFromActivity(){
        customerDashboard = (CustomerDashboard) getActivity();
        token = customerDashboard.getMyToken();

    }

    private void getAllBookings(){
        String finalToken = "Bearer " + token;
        Call<BookingsRequest> request = bookingInterface.getBookings(finalToken);
        request.enqueue(new Callback<BookingsRequest>() {
            @Override
            public void onResponse(Call<BookingsRequest> call, Response<BookingsRequest> response) {
                if(response.code() != 200){
                    Toast.makeText(getContext(), "Something went wrong with status code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                BookingsRequest res = response.body();
                statusArrayList = res.getBookings();

            }

            @Override
            public void onFailure(Call<BookingsRequest> call, Throwable t) {

            }
        });
    }

    // ============================== GET RETROFIT ============================== //
    // ============================== GET RETROFIT ============================== //

}