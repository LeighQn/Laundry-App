package com.example.laundry_app.USERS.Customer.MainFragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.laundry_app.ADAPTERS.Customer.BookingAdapter;
import com.example.laundry_app.ADAPTERS.Customer.CustomerStatusAdapter;
import com.example.laundry_app.ADAPTERS.Customer.StatusAdapter;
import com.example.laundry_app.API.INTERFACE.BookingInterface;
import com.example.laundry_app.API.INTERFACE.Customer.CustomerStatusInterface;
import com.example.laundry_app.API.INTERFACE.SalesInterface;
import com.example.laundry_app.API.MODELCLASS.BookingModel;
import com.example.laundry_app.API.MODELCLASS.BookingsRequest;
import com.example.laundry_app.API.MODELCLASS.Customer.CustomerStatusModel;
import com.example.laundry_app.APIClient;
import com.example.laundry_app.Global;
import com.example.laundry_app.MainActivity;
import com.example.laundry_app.R;
import com.example.laundry_app.USERS.Customer.CustomerDashboard;
import com.example.laundry_app.USERS.Staff.DashboardActivity;
import com.example.laundry_app.USERS.Staff.MapActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CustomerStatusFragment extends Fragment {

    CustomerDashboard customerDashboard;
    BookingInterface bookingInterface;
    SalesInterface salesInterface;
    StatusAdapter bookingAdapter;
    RecyclerView recyclerView;
    Spinner spinner;
    ImageView bg2, bg1;
    TextView txtTitle;
    private StatusAdapter.RecyclerViewClickListener listener;

    private ArrayList<BookingModel> bookingModelList = new ArrayList<BookingModel>();

    String token, finalToken, role, type;
    Retrofit retrofit = Global.setIpRetrofit(Global.getIp());
    Intent intent;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_status_staff, null);

        recyclerView = root.findViewById(R.id.rv_staff_status);
        txtTitle = (TextView) root.findViewById(R.id.txt_out_for_delivery);
        spinner = (Spinner) root.findViewById(R.id.spinner_out_for_delivery);
        bg2 = root.findViewById(R.id.img_choose_spinner_icon);
        bg1 = root.findViewById(R.id.img_comforter_spinner_bg);


        spinner.setVisibility(View.GONE);
        bg2.setVisibility(View.GONE);
        bg1.setVisibility(View.GONE);



        txtTitle.setText("LAUNDRY STATUS");


        bookingInterface = retrofit.create(BookingInterface.class);

        // ====================================== EXECUTE METHODS ====================================== //
        // ====================================== EXECUTE METHODS ====================================== //

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        bookingAdapter = new StatusAdapter();


        getDataFromActivity();
        getBookings();
        setOnClickListerner();

        return root;
    }

    private void getBookings(){
        finalToken = "Bearer " + token;
        Call<BookingsRequest> call = bookingInterface.getBookings(finalToken);
        call.enqueue(new Callback<BookingsRequest>() {
            @Override
            public void onResponse(Call<BookingsRequest> call, Response<BookingsRequest> response) {
                if(!response.isSuccessful() || response.code() != 200){
                    bookingModelList.clear();
                    return;
                }

                BookingsRequest  bookingsRequestResponse= response.body();
                bookingModelList = bookingsRequestResponse.getBookings();

                Toast.makeText(getActivity(), "Got the bookings with size: " + bookingModelList.size(), Toast.LENGTH_SHORT).show();

                bookingAdapter.setStatusDatas(bookingModelList, listener);
                recyclerView.setAdapter(bookingAdapter);



            }

            @Override
            public void onFailure(Call<BookingsRequest> call, Throwable t) {
                Log.d("SOME", t.getMessage());
            }
        });
    }


    private void setOnClickListerner(){
        listener = new StatusAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
            }
        };
    }


    private void getDataFromActivity(){
        customerDashboard = (CustomerDashboard) getActivity();
        token = customerDashboard.getMyToken();

    }

//
//    private void sendTokenToMap(){
//        intent = new Intent(getActivity(), MapActivity.class);
//        intent.putExtra("token", token);
//        startActivity(intent);
//
//    }

}